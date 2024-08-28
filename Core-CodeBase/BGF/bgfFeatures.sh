#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for BGF nodes based on node type
# Description : Creates Mos and sets attributes
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> <baseName> "

echo "Example: $0 CORE-ST-vBGF-16A-V3X5 BGF-16A-V3 "

}
neType(){

echo "ERROR: The script runs only for BGF nodes"
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
#baseName=$2
numOfNodes=`echo $simName|awk -F'x' '{print $NF}'`
Path=`pwd`
########################################################################
#To check the if simname is MRF#
########################################################################
if [[ $simName != *"BGF"* ]]
then
neType
exit 1
fi

echo "$0 started running at" $(date +%T)
########################################################################
#Reading product data from file AssignProductData
########################################################################

$Path/../bin/extractNeNames.pl $simName
neNames=( $( cat $Path/dumpNeName.txt ) )
productData=$($Path/../bin/AssignProductData.sh $simName)
productNumber=`echo $productData | cut -d ":" -f1`
productRevision=`echo $productData | cut -d ":" -f2`

for nodeName in ${neNames[@]}
do
cat >> $nodeName.mo << DEF
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/var/filem/nbi_root/PerformanceManagementReportFiles"
)
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
    nrOfAttributes 2
    "supportedCompressionTypes" Array Integer 1
         0
    "producesUtcRopFiles" Boolean true
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "ropFilenameTimestamp" Integer 1
)
DEF
cat >> bgf.mml << ABC
 .open $simName
 .select $nodeName
 .start
  kertayle:file="$Path/$nodeName.mo";
ABC
moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < bgf.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm bgf.mml
echo "$0 ended at" $( date +%T );

