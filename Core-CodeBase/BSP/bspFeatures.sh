#!/bin/bash
########################################################################
# Version     : 17.2
# Revision    :
# Purpose     : Loads features for BSP nodes based on node type
# Description : Creates Mos and sets attributes
# Date        : Dec 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name>  "

echo "Example: $0 CORE-ST-BSP-16A-V2x5 "

}
neType(){

echo "ERROR: The script runs only for BSP nodes"
}
########################################################################
#To check commandline arguments#
########################################################################
if [ $# -ne 1 ]
then
usage
exit 1
fi
########################################################################
#Variables
########################################################################
simName=$1
Path=`pwd`
########################################################################
#To check the if simname is BSP#
########################################################################
if [[ $simName != *"BSP"* ]]
then
neType
exit 1
fi

echo "$0 started running at" $(date +%T)
#######################################################################
#Extracting nodenames#
#######################################################################

$Path/../bin/extractNeNames.pl $simName
neNames=( $( cat $Path/dumpNeName.txt ) )

########################################################################
#Reading product data from file AssignProductData
########################################################################
productData=$($Path/../bin/AssignProductData.sh $simName)
productNumber=`echo $productData | cut -d ":" -f1`
productRevision=`echo $productData | cut -d ":" -f2`
########################################################################
#Making MO script#
########################################################################
for nodeName in ${neNames[@]}
do
cat >> $nodeName.mo << DEF
SET
(
    mo "ManagedElement=$nodeName"
    exception none
    nrOfAttributes 1
    "productIdentity" Array Struct 1
        nrOfElements 3
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productDesignation" String "N/A"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "$nodeName"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
        "description" String "$nodeName"
        "type" String "N/A"

)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "$nodeName"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
        "description" String "$nodeName"
        "type" String "N/A"

)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1"
     exception none
    nrOfAttributes 1
    "active" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1,BrmBackup=1"
    exception none
    nrOfAttributes 3
    "brmBackupId" String "1"
    "backupName" String "1"
)
SET
(
  mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1"
  exception none
  nrOfAttributes 2
  "backupDomain" String "Local"
  "backupType" String "LocalData"
)
CREATE
(
    parent "ManagedElement=$nodeName,SystemFunctions=1"
    identity "1"
    moType HwInventory
    exception none
    nrOfAttributes 3
    "timeOfLatestInvChange" String "2016-07-27T07:39:56+00:00"
    "hwInventoryId" String "1"
    "userLabel" String ""
)
CREATE
(
    parent "ManagedElement=$nodeName,SystemFunctions=1,HwInventory=1"
    identity "blade:0-0"
    moType HwItem
    exception none
    nrOfAttributes 18
    "vendorName" String "Ericsson AB"
    "hwModel" String "SCXB3"
    "hwType" String "blade"
    "hwName" String "Control Switch"
    "hwCapability" String ""
    "equipmentMoRef" Array Ref 0
    "additionalInformation" String ""
    "hwItemId" String "blade:0-0"
    "hwUnitLocation" String "$nodeName"
    "manualDataEntry" Integer 1
    "serialNumber" String "A065237011"
    "dateOfLastService" String "2016-07-20T20:24:01"
    "dateOfManufacture" String "2016-05-20T20:24:01"
    "swInvMoRef" Array Ref 0
    "licMgmtMoRef" Array Ref 0
    "additionalAttributes" Array Struct 0
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "CXP9025735"
        "productRevision" String "R10D"
        "productDesignation" String "SCXB3"

    "userLabel" String "null"
)
DEF
########################################################################
#Making MML script#
########################################################################
cat >> bsp.mml << ABC
.open $simName
.select $nodeName
.start
setmoattribute:mo="1",attributes="managedElementId(string)=$nodeName";
kertayle:file="$Path/$nodeName.mo";
ABC
########################################################################
moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < bsp.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm bsp.mml
echo "$0 ended at" $( date +%T );




