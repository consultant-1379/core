#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for EPG(or)vEPG nodes based on node type
# Description : Sets ProductData values by calling AssignProductData.sh script
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 CORE-ST-EPG-SSR-16B-V3x1   (or ) CORE-ST-vEPG-EVR-16B-V3x1 CORE1EPG0 "

}
neType(){

echo "ERROR: The script runs only for epg and vepg nodes"
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
#To check the if simname is EPG (or)vEPG#
########################################################################
if [[ $simName != *"EPG"* ]]
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
    mo "ComTop:ManagedElement=1"
    exception none
    nrOfAttributes 3
    "productIdentity" Array Struct 1
        nrOfElements 3
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productDesignation" String "$nodeName"

    "dnPrefix" String "SubNetwork=$nodeName,MeContext=$nodeName"
    "dateTimeOffset" String "+01:00"
)
DEF
########################################################################
#Making MML script#
########################################################################
cat >> epg.mml << ABC
.open $simName
.select $nodeName
.start
kertayle:file="$Path/$nodeName.mo";
pmdata:disable;
ABC
########################################################################
moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < epg.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm epg.mml
echo "$0 ended at" $( date +%T );

