#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for ESAPC nodes
# Description : Creates MOs and sets attribute values
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name>"

echo "Example: $0 CORE-FT-ESAPC-16B-V5x5 "

}
neType(){

echo "ERROR: The script runs only for ESAPC nodes"
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
#To check the if simname is ESAPC
########################################################################
if [[ $simName != *"ESAPC"* ]]
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
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-Brf-CXP9018859_2-R1D001"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-Brf-CXP9018859_2-R1D001"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-Brf"
        "productNumber" String "CXP9018859_2"
        "productRevision" String "R1D001"
        "productionDate" String "2016-04-07T05:12:00"
        "description" String "ERIC-Brf"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-BrfCmwA-CXP9018859_2-R1D001"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-BrfCmwA-CXP9018859_2-R1D001"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-BrfCmwA"
        "productNumber" String "CXP9018859_2"
        "productRevision" String "R1D001"
        "productionDate" String "2016-04-07T05:12:00"
        "description" String "ERIC-BrfCmwA"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-BrfLib-CXC1734454_4-R1C02"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-BrfLib-CXC1734454_4-R1C02"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-BrfLib"
        "productNumber" String "CXC1734454_4"
        "productRevision" String "R1C02"
        "productionDate" String "2016-05-20T20:58:00"
        "description" String "ERIC-BrfLib"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-CluAPI-CXC1732367_4-R1C02"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-CluAPI-CXC1732367_4-R1C02"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-CluAPI"
        "productNumber" String "CXC1732367_4"
        "productRevision" String "R1C02"
        "productionDate" String "2016-05-20T20:58:00"
        "description" String "ERIC-CluAPI"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-CluRun-CXC1732316_4-R1C02"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-CluRun-CXC1732316_4-R1C02"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-CluRun"
        "productNumber" String "CXC1732316_4"
        "productRevision" String "R1C02"
        "productionDate" String "2016-05-20T20:58:00"
        "description" String "ERIC-CluRun"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-EVIP-CXP9017652_3-R1A15"
	moType SwItem
    exception none
    nrOfAttributes 3
    "swItemId" String "ERIC-EVIP-CXP9017652_3-R1A15"
    "administrativeData" Struct
        nrOfElements 7
        "productName" String "ERIC-EVIP"
        "productNumber" String "CXP9017652_3"
        "productRevision" String "R1A15"
        "productionDate" String "2015-11-29T15:01:00"
        "description" String "ERIC-EVIP"
        "type" String "OTHER"
    "consistsOf" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-LmSa-CXP9021377_2-R1B01"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-LmSa-CXP9021377_2-R1B01"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-LmSa"
        "productNumber" String "CXP9021377_2"
        "productRevision" String "R1B01"
        "productionDate" String "2016-05-11T18:29:00"
        "description" String "ERIC-LmSa"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-SAPC_PcrfProc-CXP9030974_5-R1B41"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-SAPC_PcrfProc-CXP9030974_5-R1B41"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-SAPC_PcrfProc"
        "productNumber" String "CXP9030974_5"
        "productRevision" String "R1B41"
        "productionDate" String "2016-05-20T16:49:00"
        "description" String "ERIC-SAPC_PcrfProc"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-SAPC_SubsChargingProc-CXP9031741_5-R1B41"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-SAPC_SubsChargingProc-CXP9031741_5-R1B41"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-SAPC_SubsChargingProc"
        "productNumber" String "CXP9031741_5"
        "productRevision" String "R1B41"
        "productionDate" String "2016-05-20T16:49:00"
        "description" String "ERIC-SAPC_SubsChargingProc"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-AmfImmObjectsLib-CXC1731655_4-R1C02"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-AmfImmObjectsLib-CXC1731655_4-R1C02"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-AmfImmObjectsLib"
        "productNumber" String "CXC1731655_4"
        "productRevision" String "R1C02"
        "productionDate" String "2016-05-20T20:58:00"
        "description" String "ERIC-AmfImmObjectsLib"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-BrfImmObjectsLib-CXC1734455_4-R1C02"
	moType SwItem
    exception none
    nrOfAttributes 2
    "swItemId" String "ERIC-BrfImmObjectsLib-CXC1734455_4-R1C02"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-BrfImmObjectsLib"
        "productNumber" String "CXC1734455_4"
        "productRevision" String "R1C02"
        "productionDate" String "2016-05-20T20:58:00"
        "description" String "ERIC-BrfImmObjectsLib"
        "type" String "OTHER"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1"
	identity "ERIC-Brfp-CXP9018859_2-R1D001"
	moType SwItem
    exception none
    nrOfAttributes 3
    "swItemId" String "ERIC-Brfp-CXP9018859_2-R1D001"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "ERIC-Brfp"
        "productNumber" String "CXP9018859_2"
        "productRevision" String "R1D001"
        "productionDate" String "2016-04-07T05:12:00"
        "description" String "ERIC-Brfp"
        "type" String "OTHER"
    "consistsOf" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=1
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwSwIM:SwInventory=1,CmwSwIM:SwVersion=1"
    exception none
    nrOfAttributes 4
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "SwVersion"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
        "description" String "N/A"
        "type" String "1"
    "timeOfActivation" String "2016-05-20T20:24:01"
    "timeOfInstallation" String "2016-05-20T20:24:01"
    "consistsOf" Array Ref 14
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-Brf-CXP9018859_2-R1D001
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-AmfImmObjectsLib-CXC1731655_4-R1C02
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-Brf-CXP9018859_2-R1D001
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-BrfCmwA-CXP9018859_2-R1D001
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-BrfImmObjectsLib-CXC1734455_4-R1C02
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-BrfLib-CXC1734454_4-R1C02
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-Brfp-CXP9018859_2-R1D001
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-CluAPI-CXC1732367_4-R1C02
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-CluRun-CXC1732316_4-R1C02
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-EVIP-CXP9017652_3-R1A15
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-LmSa-CXP9021377_2-R1B01
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-SAPC_PcrfProc-CXP9030974_5-R1B41
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=ERIC-SAPC_SubsChargingProc-CXP9031741_5-R1B41
	ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "SwItem"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-21T14:29:10"
	"description" String "N/A"
        "type" String "1"

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
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,BrM:BrM=1,BrM:BrmBackupManager=1"
    exception none
    nrOfAttributes 2
    "backupType" String "BRM_SYSTEM_DATA"
    "backupDomain" String "BRM_SYSTEM_DATA"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,BrM:BrM=1,BrM:BrmBackupManager=1,BrM:BrmBackup=1"
    exception none
    nrOfAttributes 2
    "creationTime" String "2016-07-29T14:52:29"
    "backupName" String "shahzad_29072016"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:FeatureKey=1"
    exception none
    nrOfAttributes 6
    "validFrom" String "2016-03-28T00:00:00"
    "productType" String "SAPC"
    "featureKeyId" String "1"
    "expiration" String "2017-03-28T00:00:00"
    "name" String "FeaKey"
    "keyId" String "1"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:CapacityKey=1"
    exception none
    nrOfAttributes 6
    "validFrom" String "2016-03-28T00:00:00"
    "productType" String "SAPC"
    "capacityKeyId" String "1"
    "expiration" String "2017-03-28T00:00:00"
    "name" String "CapKey"
    "keyId" String "1"
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:KeyFileManagement=1"
	identity "1"
	moType KeyFileInformation
    exception none
    nrOfAttributes 5
    "keyFileInformationId" String "1"
    "productType" String "SAPC"
    "sequenceNumber" Int32 100
    "installationTime" String "2016-07-27T07:39:49"
    "locatable" Boolean true
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwM=1,UpgradePackage=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Array Struct 1
        nrOfElements 6
        "productName" String "SAPC"
        "productNumber" String "$productNumber"
        "productRevision" String "$productRevision"
        "productionDate" String "2016-06-28T07:07:52"
        "description" String "N/A"
        "type" String "SAPC"

)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,Lm_MOM:Lm=1"
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
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,CmwPm:Pm=1,CmwPm:PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/c/pm_data"
)
SET
(
    mo "ManagedElement=$nodeName,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "supportedCompressionTypes" Array Integer 1
         0
)

DEF
########################################################################
#Making MML script#
########################################################################
cat >> esapc.mml << ABC
.open $simName
.select $nodeName
.start
kertayle:file="$Path/$nodeName.mo";
pmdata:disable;
ABC
########################################################################
moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < esapc.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm esapc.mml
echo "$0 ended at" $( date +%T );

