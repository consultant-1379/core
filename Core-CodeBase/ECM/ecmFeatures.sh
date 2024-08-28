#!/bin/bash
########################################################################
# Version     : 17.3
# Revision    :
# Purpose     : Loads features for ECM nodes
# Description : Creates Mos and sets attributes
# Date        : Dec 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name>  "

echo "Example: $0 CORE-ST-ECM-16Ax2 "

}
neType(){

echo "ERROR: The script runs only ECM nodes"
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
#To check the if simname is ECM #
########################################################################
if [[ $simName != *"ECM"* ]]
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
#Making MO script#
########################################################################
for nodeName in ${neNames[@]}
do

cat >> ecm.mml << ABC
.open $simName
.select $nodeName
.start
set_ecmalarm_type:type=http;
pmdata:disable;
ABC

moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < ecm.mml

rm ecm.mml
echo "$0 ended at" $( date +%T );

