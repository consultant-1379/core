#!/bin/bash

simName=$1
baseName=$3
numOfNodes=$2
echo -e "INFO: Loading Features on the nodes\n"
if [[ $simName == *"vBGF"* ]]
then
$PWD/../BGF/bgfFeatures.sh $simName

elif [[ $simName == *"BSC.*Q"* ]]
then
$PWD/../BSC/bscFeatures.sh $simName

elif [[ $simName == *"BSP"* ]]
then
$PWD/../BSP/bspFeatures.sh $simName

elif [[ $simName == *"C608"* ]]
then
$PWD/../C608/c608Features.sh $simName

elif [[ $simName == *"CSCF"* ]]
then
$PWD/../CSCF/cscfFeatures.sh $simName
cd $PWD/../CSCF/
echo -e "INFO:Running attributes jar\n"
java -cp setMoAttributes.jar com.ericsson.simnet.core.Main $simName $numOfNodes $baseName
echo -e "INFO: loaded all attributes successfully\n"
cd $PWD

elif [[ $simName == *"DSC"* ]]
then
$PWD/../DSC/dscFeatures.sh $simName

elif [[ $simName == *"ECM"* ]]
then
$PWD/../ECM/ecmFeatures.sh $simName

elif [[ $simName == *"vEME"* ]]
then
$PWD/../EME/emeFeatures.sh $simName

elif [[ $simName == *"EPG"* || $simName == *"vEPG"* ]]
then
$PWD/../EPG/EpgFeatures.sh $simName

elif [[ $simName == *"ESAPC"* ]]
then
$PWD/../ESAPC/vSAPCFeatures.sh $simName

elif [[ $simName == *"FrontHaul"* ]]
then
$PWD/../FronthAul/FronthAulFeatures.sh $simName

elif [[ $simName == *"HSS-FE"* ]]
then
$PWD/../HSS/hssFeatures.sh $simName

elif [[ $simName == *"IPWORKS"* ]]
then
$PWD/../IPWORKS/ipWorks.sh $simName

elif [[ $simName == *"MGw"* ]]
then
$PWD/../MGw/MGwFeatures.sh $simName

elif [[ $simName == *"ML"* ]]
then
$PWD/../MiniLink/miniLinkFeatures.sh $simName

elif [[ $simName == *"MRFv"* ]]
then
$PWD/../MRF/mrfFeatures.sh $simName

elif [[ $simName == *"MTAS"* ]]
then
$PWD/../MTAS/mtasFeatures.sh $simName

elif [[ $simName == *"SBG"* ]]
then
$PWD/../SBG/sbgFeatures.sh $simName

elif [[ $simName == *"SGSN"* ]]
then
$PWD/../Sgsn/sgsnFeatures.sh $simName
cd $PWD/../Sgsn/
echo -e "INFO:Running attributes jar"
java -cp setMoAttributes.jar com.ericsson.simnet.core.Main $simName $numOfNodes $baseName
echo -e "INFO: loaded all attributes successfully"
cd $PWD

elif [[ $simName == *"Spit"* || $simName == *"R6675"* || $simName == *"R6274"* || $simName == *"R6371"* || $simName == *"R6471-1"* || $simName == *"R6471-2"* ]]
then
$PWD/../SpitFire/SpitFireFeatures.sh $simName

elif [[ $simName == *"TCU04"* ]]
then
$PWD/../TCU/TCUFeatures.sh $simName


elif [[ $simName == *"vWCG"* ]]
then
$PWD/../WCG/wcgFeatures.sh $simName

elif [[ $simName == *"WMG"* || $simName == *"vWMG"* ]]
then
$PWD/../WMG/wmgFeatures.sh $simName

elif [[ $simName == *"UPG"* ]]
then
$PWD/../UPG/upgFeatures.sh $simName

fi
