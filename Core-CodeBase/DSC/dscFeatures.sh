#!/bin/bash
########################################################################
# Version     : 17.3
# Revision    :
# Purpose     : Loads features for DSC nodes based on node type
# Description : Creates Mos and sets attributes
# Date        : Jan 2017
# Who         : xmedkar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 CORE-ST-DSC-16B-V4x1"

}
neType(){

echo "ERROR: The script runs only for DSC nodes"
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
#To check the if simname is DSC#
########################################################################
if [[ $simName != *"DSC"* ]]
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
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/var/filem/nbi_root/PerformanceManagementReportFiles"
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
  mo "ManagedElement=$nodeName,SystemFunctions=1,BrM=1,BrmBackupManager=1"
  exception none
  nrOfAttributes 2
  "backupDomain" String "Local"
  "backupType" String "LocalData"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 2
    "producesUtcRopFiles" Boolean true
    "ropFilenameTimestamp" Integer 1
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1"
    exception none
    nrOfAttributes 1
    "consistsOf" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1"
    exception none
    nrOfAttributes 1
    "consistsOf" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1
)
DEF
########################################################################
#Making MML script#
########################################################################
cat >> dsc.mml << ABC
.open $simName
.select $nodeName
.start
kertayle:file="$Path/$nodeName.mo";
ABC
########################################################################
moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < dsc.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm dsc.mml
echo "$0 ended at" $( date +%T );

