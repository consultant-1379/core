#!/bin/bash
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 SGSN-WPP-14B-V6x30 "

}
######################################################
#To check whether commands are passed as they should#
######################################################
if [ $# -ne 1 ]
then
usage
exit
fi
simName=$1
path=`pwd`

tempSimName=(${simName//-/ })

#find length of tempSimName
a=${#tempSimName[@]}

#Get mimVersion from simname for each version

if [[ $simName == *"MGw"* ]]
then
mimVersion=${tempSimName[a-2]}

elif [[ $simName == *"SGSN"* ]]
then
mimVersion=${tempSimName[a-4]}-${tempSimName[a-3]}-${tempSimName[a-2]}

elif [[ $simName == *"Spit"* || $simName == *"R6675"* || $simName == *"R6274"* || $simName == *"R6371"* || $simName == *"R6471-1"* || $simName == *"R6471-2"* ]]
then
mimVersion=${tempSimName[2]}-${tempSimName[3]}

elif [[ $simName == *"EPG"* || $simName == *"vEPG"* ]]
then
mimVersion=${tempSimName[a-4]}-${tempSimName[a-3]}-${tempSimName[a-2]}

elif [[ $simName == *"TCU"* ]]
then
if [[ $simName == *"TCU04"* ]]
then
mimVersion=TCU04`echo $simName | awk -F"TCU04" '{print $2}' | awk -F"x" '{print $1}'`
fi

elif [[ $simName == *"UPGIND"* ]]
then
if [[ $simName == *"MTAS"* || $simName == *"ESAPC"* || $simName == *"SBG"* ]]
then
mimVersion=${tempSimName[a-4]}-${tempSimName[a-3]}-${tempSimName[a-2]}
fi

elif [[ $simName == *"ESAPC"* || $simName == *"MTAS"*
     || $simName == *"CSCF"* || $simName == *"UPG"*
     || $simName == *"DSC"* || $simName == *"BSP"* ||  $simName == *"SBG"* || $simName == *"vWMG"* || $simName == *"vWCG"* || $simName == *"vEME"* || $simName == *"IPWORKS"* || $simName == *"vBGF"*  || $simName == *"MRFv"*
     || $simName == *"HSS-FE"* ]]
then
mimVersion=${tempSimName[a-3]}-${tempSimName[a-2]}

elif [[ $simName == *"WMG"* ]]
then
mimVersion=${tempSimName[a-4]}-${tempSimName[a-3]}-${tempSimName[a-2]}


elif [[ $simName == *"FrontHaul"* ]]
then
mimVersion=${tempSimName[a-4]}-${tempSimName[a-3]}
else
exit 1
fi

#extract Product number and product version using mimversion from productdata.env

grep -i $mimVersion"=" /$path/../bin/ProductData.env > $path/ProductData.txt
mimLine=`cat $path/ProductData.txt`
number=`echo "$mimLine" | grep -o -P '(?<==).*(?=:)'`
revision=`echo "$mimLine" | cut -d ":" -f2`
echo "$number:$revision"
rm -rf *.txt
