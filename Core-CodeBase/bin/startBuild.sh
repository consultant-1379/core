#!/bin/bash

usage (){

echo "Usage  : $0 <sim name> <netypeLink> <basename> <numofnodes> "

echo "Example: $0 CORE-ST-4.5K-SGSN-16A-CP03x4 ftp://ftp.lmera.ericsson.se/project/netsim-ftp/simulations/NEtypes/com3.1/SGSN_16A-CP02-WPP-V3_R29D.zip CORE09SGSN 4
"

}
########################################################################
#To check commandline arguments#
########################################################################
if [ $# -ne 4 ]
then
usage
exit 1
fi
simName=$1
nodeSupportLink=$2
baseName=$3
numOfNodes=$4
Path=`pwd`
######################
#Logs
####################### 
DATE=`date +%F`
TIME=`date +%T`
LOGFILE=$PWD/log/BuildCoreLogs_$DATE\_$TIME.log

cd /netsim/netsimdir

wget $nodeSupportLink
nodeSupport=`echo $nodeSupportLink | rev | cut -d "/" -f1 | rev`
nodeSupportWithoutzip=`echo $nodeSupport | cut -d '.' -f1`
echo "nodeSupportSim is $nodeSupportWithoutzip"
rm -rf /netsim/netsimdir/${nodeSupport}.zip 
rm -rf /netsim/netsimdir/${nodeSupportWithoutzip} 
echo -e ".uncompressandopen ${nodeSupport} force" | /netsim/inst/netsim_pipe | tee -a $LOGFILE
neType=`echo -e ".open $nodeSupportWithoutzip \n.show simnes" | /netsim/inst/netsim_pipe |  awk '/OK/{f=0;};f{print $2 " " $3 " " $4;};/NE Name/{f=1;}'`
echo "$neType"

cd $Path
echo -e "Running Automation jar to build $simName\n"
java -jar coreAutomation.jar $simName "$neType" $baseName $numOfNodes | tee -a $LOGFILE
rm -rf *.mo *.mml

$Path/loadFeatures.sh $simName $numOfNodes $baseName | tee -a $LOGFILE
#echo -e ".uncompressandopen ${nodeSupport} force" | /netsim/inst/netsim_pipe
echo -e ".open $simName \n.saveandcompress $simName force " | /netsim/inst/netsim_pipe | tee -a $LOGFILE
echo -e "INFO: Simulation build is completed"
rm -rf *.mml *.mo


