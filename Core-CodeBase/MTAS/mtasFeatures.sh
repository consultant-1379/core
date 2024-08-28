#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for MTAS nodes based on node type
# Description : Creates Mos and sets attributes
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 CORE-ST-MTAS-16A-V3x5"

}
neType(){

echo "ERROR: The script runs only for MTAS nodes"
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
#To check the if simname is MTAS#
########################################################################
if [[ $simName != *"MTAS"* ]]
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
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwPm:Pm=1,CmwPm:PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/PerformanceManagementReportFiles"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1"
    exception none
    nrOfAttributes 2
    "backupDomain" String "NotNull"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1,BrmBackup=1"
    exception none
    nrOfAttributes 3
    "creationType" Integer 3
    "brmBackupId" String "1"
    "backupName" String "1"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 3
    "supportedCompressionTypes" Array Integer 1
         0
    "producesUtcRopFiles" Boolean true
    "ropFilenameTimestamp" Integer 1
)
SET
(
  mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1"
  exception none
  nrOfAttributes 2
  "backupDomain" String "Local"
  "backupType" String "LocalData"
)
DEF
########################################################################
#Making MML script#
########################################################################
cat >> mtas.mml << ABC
.open $simName
.select $nodeName
.start
setmoattribute:mo="1",attributes="managedElementId(string)=$nodeName";
kertayle:file="$Path/$nodeName.mo";
pmdata:disable;
ABC
########################################################################

moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < mtas.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm mtas.mml
echo "$0 ended at" $( date +%T );

