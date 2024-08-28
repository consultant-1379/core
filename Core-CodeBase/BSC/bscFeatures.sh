#!/bin/bash
########################################################################
# Version     : 17.12
# Revision    :
# Purpose     : Loads features for BSC nodes
# Description : Creates and Sets MOs
# Date        : July 2017
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 GSM-FT-BSC_17-Q3_V2x2 "

}
neType(){

echo "ERROR: The script runs only for BSC nodes"
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
#######################################################################
#Extracting nodenames#
######################################################################

$Path/../bin/extractNeNames.pl $simName
neNames=( $( cat $Path/dumpNeName.txt ) )

########################################################################
#To check the if simname belongs to BSC type#
########################################################################
if [[ $simName != *"BSC"* ]]
then
neType
exit 1
fi

echo "$0 started running at" $(date +%T)
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
    "productIdentity" Array Struct 0
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "BSC01"
        "productNumber" String "3.0.0"
        "productRevision" String "PA01"
        "productionDate" String "07-07-2017"
        "description" String ""
        "type" String "N/A"

)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "BSC01"
        "productNumber" String "3.0.0"
        "productRevision" String "PA01"
        "productionDate" String "07-07-2017"
        "description" String ""
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
    mo "ManagedElement=$nodeName,SystemFunctions=1,SecM=1,CertM=1,CertMCapabilities=1"
    exception none
    nrOfAttributes 2
    "fingerprintSupport" Integer 0
	"enrollmentSupport" Array Integer 4
         0
         1
         2
         3
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/apfs/cdh/cdhdefault/Ready"
)

DEF
########################################################################
#Making MML script#
########################################################################
cat >> bsc.mml << ABC
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

/netsim/inst/netsim_pipe < bsc.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm bsc.mml 
echo "$0 ended at" $( date +%T );


