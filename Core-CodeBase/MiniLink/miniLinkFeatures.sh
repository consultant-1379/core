#!/bin/bash
########################################################################
# Version     : 17.12
# Revision    :
# Purpose     : Loads features for MiniLink nodes
# Description : Sets alarm commands for Minilink nodes
# Date        : July 2017
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name>  "

echo "Example: $0 ML6352-R2-8x2-CORE42 "

}
neType(){

echo "ERROR: The script runs only for MiniLink nodes"
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
#To check the if simname belongs to MiniLink type#
########################################################################
if [[ $simName != *"ML"* ]]
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
cat >> MiniLink.mml << ABC
.open $simName
.select $nodeName
.start
alarmType:type="enm";
alarmType:status;
pmdata:disable;
ABC
########################################################################
done

/netsim/inst/netsim_pipe < MiniLink.mml
rm MiniLink.mml
echo "$0 ended at" $( date +%T );

