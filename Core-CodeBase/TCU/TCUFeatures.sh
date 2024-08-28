#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for TCU nodes
# Description : Creates MOs and sets attribute values
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name>  "

echo "Example: $0 GSM-TCU04-T16B-V6x5"

}
neType(){

echo "ERROR: The script runs only for TCU nodes"
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
#To check the if simname is TCU
########################################################################
if [[ $simName != *"TCU"* ]]
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
DELETE
(
  mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1,RcsSwIM:SwVersion=1"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP102172/2-R20B01"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP102172/2-R20B01"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "TAIPAN"
        "productNumber" String "CXP102172/2"
        "productRevision" String "R20B01"
        "productionDate" String "2016-03-16T23:50:23"
        "description" String "Load Module Container for TAIPAN,CXP102172_2 R20B01"
        "type" String "RBSG2"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP102185/1-R14B01"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP102185/1-R14B01"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "KATLA"
        "productNumber" String "CXP102185/1"
        "productRevision" String "R14B01"
        "productionDate" String "2016-03-07T22:31:57"
        "description" String "Load Module Container for KATLA,CXP102185_1 R14B01"
        "type" String "RBSG2"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9024020/1-R1AS"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9024020/1-R1AS"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "EMCLITOOL"
        "productNumber" String "CXP9024020/1"
        "productRevision" String "R1AS"
        "productionDate" String "2016-05-05T21:09:54"
        "description" String "EMCLITOOL application "
        "type" String "Client application EMCLITOOL to start moshell"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9024262/5-R1SE"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9024262/5-R1SE"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Baseband-T_MOMCPI"
        "productNumber" String "CXP9024262/5"
        "productRevision" String "R1SE"
        "productionDate" String "2016-06-28T03:50:22"
        "description" String "OAM documents for RBS 6000"
        "type" String "Document container"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9024812/5-R1E"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9024812/5-R1E"
    "userLabel" String "null"
     "administrativeData" Struct
        nrOfElements 6
        "productName" String "ARCHSUPP_PLAB"
        "productNumber" String "CXP9024812/5"
        "productRevision" String "R1E"
        "productionDate" String "2016-05-30T14:50:34"
        "description" String "Platform abstractions"
        "type" String "Compiled for armhf and i686"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025317/5-R6F07"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9025317/5-R6F07"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "RCSEE-T"
        "productNumber" String "CXP9025317/5"
        "productRevision" String "R6F07"
        "productionDate" String "2016-06-27T14:12:23"
        "description" String "RBS Control System and OS for RCP, CSX10179/1 R6F"
        "type" String "Part of the RBS platform"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025783/25-R25ED"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9025783/25-R25ED"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "MOFWK_TCU03"
        "productNumber" String "CXP9025783/25"
        "productRevision" String "R25ED"
        "productionDate" String "2015-11-09T00:00:00"
        "description" String "MOFWK. Gives applications access to configuration data from IMM. Compiled for tcu03. Used at runtime. "
        "type" String "Part of CAT, IWAS and RBS 6000 G2. Compiled for arm?"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025783/26-R26NJ"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9025783/26-R26NJ"
    "userLabel" String "null"
     "administrativeData" Struct
        nrOfElements 6
        "productName" String "MOFWK_TCU03"
        "productNumber" String "CXP9025783/26"
        "productRevision" String "R26NJ"
        "productionDate" String "2016-06-13T00:00:00"
        "description" String "Gives applications access to configuration data from IMM. Compiled for tcu03. Used at runtime."
        "type" String "Part of CAT, IWAS and RBS 6000 G2. Compiled for arm?"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9026393/1-R3AE"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9026393/1-R3AE"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "EMGUI"
        "productNumber" String "CXP9026393/1"
        "productRevision" String "R3AE"
        "productionDate" String "2016-06-22T11:58:35"
        "description" String "EMGUI application"
        "type" String "Client application EMGUI to perform basic node troubleshooting"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9028406/5-R1CZ"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9028406/5-R1CZ"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "AIS_SFA-ARM"
        "productNumber" String "CXP9028406/5"
        "productRevision" String "R1CZ"
        "productionDate" String "2016-06-27T11:19:06"
        "description" String "AIS_SFA-ARM load module container (LMC)"
        "type" String "For the RBS Control System, compiled for arch=arm, os=linux, ltt=cs"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025783/27-R27TE"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9025783/27-R27TE"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "MOFWK_TCU03"
        "productNumber" String "XP9025783/27"
        "productRevision" String "R27TE"
        "productionDate" String "2016-06-22T00:00:00"
        "description" String "MOFWK. Gives applications access to configuration data from IMM. Compiled for tcu03. Used at runtime. "
        "type" String "Part of CAT, IWAS and RBS 6000 G2. Compiled for arm?"

    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "${productNumber}-${productRevision}"
    moType SwVersion
    exception none
    nrOfAttributes 6
    "swVersionId" String "${productNumber}-${productRevision}"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "SwVersion"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
	"description" String "$nodeName"
        "type" String "1"

    "timeOfActivation" String "2016-05-20T20:24:01"
    "timeOfInstallation" String "2016-05-20T20:24:01"
    "consistsOf" Array Ref 12
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP102172/2-R20B01
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP102185/1-R14B01
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9024020/1-R1AS
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9024262/5-R1SE
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9024812/5-R1E
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025317/5-R6F07
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025783/25-R25ED
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025783/26-R26NJ
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025783/27-R27TE
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9026393/1-R3AE
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9028406/5-R1CZ
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1"
    identity "1"
    moType HwInventory
    exception none
    nrOfAttributes 1
    "hwInventoryId" String "1"
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "1"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "1"
    "vendorName" String "Ericsson"
    "serialNumber" String "D16Q705501"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "KDU137815/11"
        "productRevision" String "R1A/A"
        "productDesignation" String "Baseband T605"
    "productData" Struct
        nrOfElements 6
        "productName" String "Baseband T605"
        "productNumber" String "KDU137815/11"
        "productRevision" String "R1A/A"
        "productionDate" String "2015-08-07T00:00:00+00:00"
	"description" String "Baseband"
        "type" String "FieldReplaceableUnit"

    "hwType" String "FieldReplaceableUnit"
    "hwType" String "FieldReplaceableUnit"
    "hwModel" String "Baseband"
    "hwCapability" String "Baseband T605"
    "dateOfManufacture" String "2015-08-07T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "2"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "2"
    "vendorName" String "Ericsson"
    "serialNumber" String "N/A"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "BKV 106 184/1"
        "productRevision" String "N/A"
        "productDesignation" String "FAN"

    "productData" Struct
        nrOfElements 6
        "productName" String "FAN"
        "productNumber" String "BKV 106 184/1"
        "productRevision" String "N/A"
	"productionDate" String "2015-08-07T00:00:00+00:00"
	"description" String "FAN"
        "type" String "type"

    "hwType" String "SupportUnit"
    "hwName" String "SupportUnit"
    "hwModel" String "FAN"
    "hwCapability" String "FAN"
    "dateOfManufacture" String "2015-08-07T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "3"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "3"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100210"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
	"productRevision" String "N/A"
        "productDesignation" String "LCP-10G3A4EDREN"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
	"description" String "DELTA"
	"productRevision" String "N/A"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "4"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "4"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100218"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "LCP-10G3A4EDREN"
	"productRevision" String "N/A"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
	"productRevision" String "N/A"
	"description" String "LCP"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "5"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "5"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100250"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "LCP-10G3A4EDREN"
	"productRevision" String "N/A"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
	"productRevision" String "N/A"
	"description" String "LCP"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "6"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "6"
    "vendorName" String "OCLARO, INC."
    "serialNumber" String "L14A58214"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "TRS2001EN-0039"
        "productRevision" String "N/A"
    "productData" Struct
        nrOfElements 6
        "productName" String "TRS2001EN-0039"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2014-01-18T00:00:00+00:00"
        "type" String "SfpModule"
	"productRevision" String "N/A"
	"description" String "TRS"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "TRS2001EN-0039"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2014-01-18T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "7"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "7"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1Y27"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "8"
    moType HwItem
    exception none
   nrOfAttributes 10
    "hwItemId" String "8"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PPG1X6S"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2013-04-17T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2013-04-17T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "9"
    moType HwItem
    exception none
     nrOfAttributes 10
    "hwItemId" String "9"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1QX9"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "10"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "10"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PKK02S6"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"
    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2011-05-02T00:00:00+00:00"
        "type" String "SfpModule"
       "description" String "RDH"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2011-05-02T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "11"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "11"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1XZA"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"
    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=1"
    identity "1"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "1"
    "vendorName" String "Ericsson"
    "serialNumber" String "D16Q705501"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "KDU137815/11"
        "productRevision" String "R1A/A"
        "productDesignation" String "Baseband T605"
    "productData" Struct
        nrOfElements 6
        "productName" String "Baseband T605"
        "productNumber" String "KDU137815/11"
        "productRevision" String "R1A/A"
        "productionDate" String "2015-08-07T00:00:00+00:00"
        "description" String "Baseband"
        "type" String "FieldReplaceableUnit"

    "hwType" String "FieldReplaceableUnit"
    "hwType" String "FieldReplaceableUnit"
    "hwModel" String "Baseband"
    "hwCapability" String "Baseband T605"
    "dateOfManufacture" String "2015-08-07T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=2"
    identity "2"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "2"
    "vendorName" String "Ericsson"
    "serialNumber" String "N/A"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "BKV 106 184/1"
        "productRevision" String "N/A"
        "productDesignation" String "FAN"

    "productData" Struct
        nrOfElements 6
        "productName" String "FAN"
        "productNumber" String "BKV 106 184/1"
        "productRevision" String "N/A"
        "productionDate" String "2015-08-07T00:00:00+00:00"
        "description" String "FAN"
        "type" String "type"

    "hwType" String "SupportUnit"
    "hwName" String "SupportUnit"
    "hwModel" String "FAN"
    "hwCapability" String "FAN"
    "dateOfManufacture" String "2015-08-07T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=3"
    identity "3"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "3"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100210"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productRevision" String "N/A"
        "productDesignation" String "LCP-10G3A4EDREN"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "DELTA"
        "productRevision" String "N/A"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=4"
    identity "4"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "4"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100218"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "LCP-10G3A4EDREN"
        "productRevision" String "N/A"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
        "productRevision" String "N/A"
        "description" String "LCP"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=5"
    identity "5"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "5"
    "vendorName" String "DELTA"
    "serialNumber" String "152409100250"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "LCP-10G3A4EDREN"
        "productRevision" String "N/A"

    "productData" Struct
        nrOfElements 6
        "productName" String "LCP-10G3A4EDREN"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2015-06-13T00:00:00+00:00"
        "type" String "SfpModule"
        "productRevision" String "N/A"
        "description" String "LCP"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "LCP-10G3A4EDREN"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2015-06-13T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=6"
    identity "6"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "6"
    "vendorName" String "OCLARO, INC."
    "serialNumber" String "L14A58214"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH10250/1"
        "productDesignation" String "TRS2001EN-0039"
        "productRevision" String "N/A"
    "productData" Struct
        nrOfElements 6
        "productName" String "TRS2001EN-0039"
        "productNumber" String "RDH10250/1"
        "productionDate" String "2014-01-18T00:00:00+00:00"
        "type" String "SfpModule"
        "productRevision" String "N/A"
        "description" String "TRS"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "TRS2001EN-0039"
    "hwCapability" String "LCP-10G3A4EDREN"
    "dateOfManufacture" String "2014-01-18T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=7"
    identity "7"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "7"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1Y27"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=8"
    identity "8"
    moType HwItem
    exception none
   nrOfAttributes 10
    "hwItemId" String "8"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PPG1X6S"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2013-04-17T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2013-04-17T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=9"
    identity "9"
    moType HwItem
    exception none
     nrOfAttributes 10
    "hwItemId" String "9"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1QX9"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"

    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=10"
    identity "10"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "10"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PKK02S6"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"
    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2011-05-02T00:00:00+00:00"
        "type" String "SfpModule"
       "description" String "RDH"

    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2011-05-02T00:00:00+00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1,RcsHwIM:HwItem=11"
    identity "11"
    moType HwItem
    exception none
    nrOfAttributes 10
    "hwItemId" String "11"
    "vendorName" String "FINISAR CORP."
    "serialNumber" String "PTS1XZA"
    "productIdentity" Struct
        nrOfElements 3
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productDesignation" String "FTLF8519P3BNL-E1"
    "productData" Struct
        nrOfElements 6
        "productName" String "FTLF8519P3BNL-E1"
        "productNumber" String "RDH90120/42009"
        "productRevision" String "R4A"
        "productionDate" String "2015-06-27T00:00:00+00:00"
        "type" String "SfpModule"
        "description" String "RDH"
    "hwType" String "SfpModule"
    "hwName" String "SFP module"
    "hwModel" String "FTLF8519P3BNL-E1"
    "hwCapability" String "FTLF8519P3BNL-E1"
    "dateOfManufacture" String "2015-06-27T00:00:00+00:00"

)
SET
(
    mo "ComTop:ManagedElement=$nodeName"
    exception none
    nrOfAttributes 1
    "productIdentity" Array Struct 1
        nrOfElements 3
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productDesignation" String "$nodeName"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    exception none
    nrOfAttributes 2
    "active" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=${productNumber}-${productRevision}
	"userLabel" String "$nodeName"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1"
    exception none
    nrOfAttributes 2
    "swItemId" String "1"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "SwItem"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
        "description" String "$nodeName"
        "type" String "1"

)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    exception none
    nrOfAttributes 1
    "fingerprint" String "$nodeName-fp"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SecM=1,CertM=1,CertMCapabilities=1"
    exception none
    nrOfAttributes 1
    "enrollmentSupport" Array Integer 1
         3
)
DELETE
(
  mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1,RcsLM:CapacityKey=1"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwM:SwM=1,RcsSwM:UpgradePackage=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Array Struct 1
        nrOfElements 6
        "productName" String "Baseband-T"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-28T07:07:52"
        "description" String "N/A"
        "type" String "RadioTNode"

)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsBrM:BrM=1,RcsBrM:BrmBackupManager=1"
    exception none
    nrOfAttributes 2
    "backupType" String "Systemdata"
    "backupDomain" String "System"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsBrM:BrM=1,RcsBrM:BrmBackupManager=1,RcsBrM:BrmBackup=1"
    exception none
    nrOfAttributes 2
    "creationTime" String "2016-07-27T07:39:56+00:00"
    "backupName" String "Auto integration backup - SITE_CONFIG_COMPLETE"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1,RcsLM:FeatureKey=1"
    exception none
    nrOfAttributes 5
    "validFrom" String "2016-03-28T00:00:00"
    "productType" String "BasebandT"
    "expiration" String "2017-03-28T00:00:00"
    "featureKeyId" String "1"
    "keyId" String "CXC4011649_1"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1,RcsLM:KeyFileManagement=1,RcsLM:KeyFileInformation=1"
    exception none
    nrOfAttributes 3
    "sequenceNumber" Int32 1010
    "installationTime" String "2016-07-27T07:39:49"
    "productType" String "BasebandT"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsPm:Pm=1,RcsPm:PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/c/pm_data"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SecM=1,Ssh=1"
    exception none
    nrOfAttributes 6
    "supportedKeyExchanges" Array String 2
        "ssh-rsa"
        "ssh-dss"
    "supportedCiphers" Array String 5
        "aes256-ctr"
        "aes192-ctr"
        "aes128-ctr"
        "aes128-cbc"
        "3des-cbc"
    "supportedMacs" Array String 3
        "hmac-sha2-256"
        "hmac-sha2-512"
        "hmac-sha1"
    "selectedKeyExchanges" Array String 2
        "ssh-rsa"
        "ssh-dss"
    "selectedCiphers" Array String 5
        "aes256-ctr"
        "aes192-ctr"
        "aes128-ctr"
        "aes128-cbc"
        "3des-cbc"
    "selectedMacs" Array String 3
        "hmac-sha2-256"
        "hmac-sha2-512"
        "hmac-sha1"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SecM=1,Tls=1"
    exception none
    nrOfAttributes 1
    "supportedCiphers" Array Struct 11
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-RSA-AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-DSS-AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-RSA-DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-DSS-DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-RSA-AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-DSS-AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-RSA-DES-CBC-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "DES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "DES-CBC-SHA"
        "protocolVersion" String "SSLv3"
    "enabledCiphers" Array Struct 11
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-RSA-AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-DSS-AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "AES256-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-RSA-DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-DSS-DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "3DES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "DES-CBC3-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-RSA-AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aDSS"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "DHE-DSS-AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "AES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "AES128-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "DES"
        "export" String ""
        "keyExchange" String "kDH"
        "mac" String "SHA1"
        "name" String "EDH-RSA-DES-CBC-SHA"
        "protocolVersion" String "SSLv3"
      nrOfElements 7
        "authentication" String "aRSA"
        "encryption" String "DES"
        "export" String ""
        "keyExchange" String "kRSA"
        "mac" String "SHA1"
        "name" String "DES-CBC-SHA"
        "protocolVersion" String "SSLv3"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:Transport=1"
    identity "1"
    moType RtnL3Router:Router
    exception none
    nrOfAttributes 1
    "routerId" String "1"
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:Transport=1,RtnL3Router:Router=1"
    identity "1"
    moType RtnTwampInitiator:TwampInitiator
    exception none
    nrOfAttributes 1
    "twampInitiatorId" String "1"
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:Transport=1,RtnL3Router:Router=1,RtnTwampInitiator:TwampInitiator=1"
    identity "1"
    moType RtnTwampInitiator:TwampTestSession
    exception none
    nrOfAttributes 1
    "twampTestSessionId" String "1"
)
DEF

cat >> tcu.mml << ABC
.open $simName
.select $nodeName
.start
kertayle:file="$Path/$nodeName.mo";
pmdata:disable;
ABC

moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < tcu.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm tcu.mml
echo "$0 ended at" $( date +%T );

