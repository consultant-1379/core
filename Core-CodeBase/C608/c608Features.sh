#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for C608 nodes
# Description : Creates MOs and sets attribute values
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 GSM-C608-T16B-V6x5 "

}
neType(){

echo "ERROR: The script runs only for C608 nodes"
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
#To check the if simname is c608
########################################################################
if [[ $simName != *"C608"* ]]
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

cat >> $nodeName.mo << DEF
DELETE
(
  mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1,RcsSwIM:SwVersion=1"
)

CREATE
(
    parent "ComTop:ManagedElement=CORE15C60801,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025317/5-R8C13"
    moType SwItem
    exception none
    nrOfAttributes 5
   "swItemId" String "CXP9025317/5-R8C13"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "RCSEE-T"
        "productNumber" String "CXP9025317/5"
        "productRevision" String "R8C13"
        "productionDate" String "2016-11-25T11:51:05"
        "description" String "RBS Control System and OS for RCP, CSX10179/1 R8C"
        "type" String "Part of the RBS platform"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
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
    identity "CXP102185/1-R15A01"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP102185/1-R15A01"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "KATLA"
        "productNumber" String "CXP102185/1"
        "productRevision" String "R15A01"
        "productionDate" String "2016-10-17T13:18:41"
        "description" String "Load Module Container for KATLA,CXP102185_1 R15A01"
        "type" String "RBSG2"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9021691/3-R8C07"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9021691/3-R8C07"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "DUMMY-ARM"
        "productNumber" String "CXP9021691/3"
        "productRevision" String "R8C07"
        "productionDate" String "2016-11-25T11:51:43"
        "description" String "RBS Control System and OS for RCP, CSX10179/1 R8C"
        "type" String "Part of the RBS platform"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9025546/3-R8C15"
    moType SwItem
    exception none
    nrOfAttributes 5
    "swItemId" String "CXP9025546/3-R8C15"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "RCSMW-ARM"
        "productNumber" String "CXP9025546/3"
        "productRevision" String "R8C15"
        "productionDate" String "2016-11-25T13:02:31"
        "description" String "RBS Control System and OS for RCP, CSX10179/1 R8C"
        "type" String "Part of the RBS platform"
    "additionalInfo" String "null"
    "consistsOf" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "CXP9031274/5-R8C13"
    moType SwItem
    exception none
    nrOfAttributes 4
    "swItemId" String "CXP9031274/5-R8C13"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "RCS-T"
        "productNumber" String "CXP9031274/5"
        "productRevision" String "R8C13"
        "productionDate" String "2016-11-25T12:07:37"
        "description" String "RBS Control System and OS for RCP, CSX10179/1 R8C"
        "type" String "Part of the RBS platform"
    "additionalInfo" String "bundle"
  "consistsOf" Array Ref 1
     ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025317/5-R8C13
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwIM:SwInventory=1"
    identity "nodeContainer-PZ99"
    moType SwVersion
    exception none
    nrOfAttributes 6
    "swVersionId" String "nodeContainer-PZ99"
    "userLabel" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Baseband-T"
        "productNumber" String "nodeContainer"
        "productRevision" String "PZ99"
        "productionDate" String "2016-11-28T19:40:09"
        "description" String "N/A"
        "type" String "RadioTNode"

    "timeOfActivation" String "2016-11-29T14:12:02+00:00"
    "timeOfInstallation" String "2016-11-29T11:54:57+00:00"
    "consistsOf" Array Ref 5
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP102185/1-R15A01
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9025546/3-R8C15
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9021691/3-R8C07
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP9031274/5-R8C13
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwItem=CXP102172/2-R20B01
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsHwIM:HwInventory=1"
    identity "1"
    moType HwItem
    exception none
    nrOfAttributes 2
    "hwItemId" String "1"
    "productData" Struct
        nrOfElements 6
        "productName" String "Baseband C608"
        "productNumber" String "KDU137989/1"
        "productRevision" String "R1A"
        "productionDate" String "20161115"
        "type" String "FieldReplaceableUnit"
        "description" String "RDH"
)

CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010071/4002_1"
    exception none
    nrOfAttributes 9
    moType RcsLM:CapacityKey
    "capacityKeyId" String "CXC4010071/4002_1"
    "licensedCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 1
        "noLimit" Boolean false

    "grantedCapacityLevel" Int32 1
    "licensedCapacityLimitReached" Boolean false
    "state" Ref "null"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010071/4002"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010071/4003_1"
    exception none
    nrOfAttributes 9
    moType RcsLM:CapacityKey
    "capacityKeyId" String "CXC4010071/4003_1"
    "licensedCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 1
        "noLimit" Boolean false

    "grantedCapacityLevel" Int32 1
    "licensedCapacityLimitReached" Boolean false
    "state" Ref "null"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010071/4003"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010608_1"
    exception none
    nrOfAttributes 9
    moType RcsLM:CapacityKey
    "capacityKeyId" String "CXC4010608_1"
    "licensedCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 3500
        "noLimit" Boolean false

    "grantedCapacityLevel" Int32 3500
    "licensedCapacityLimitReached" Boolean false
    "state" Ref "null"
    "expiration" String "2017-10-03T00:00:00"

    "keyId" String "CXC4010608"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010623_1"
    exception none
    nrOfAttributes 9
    moType RcsLM:CapacityKey
    "capacityKeyId" String "CXC4010623_1"
    "licensedCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 65535
        "noLimit" Boolean false

    "grantedCapacityLevel" Int32 65535
    "licensedCapacityLimitReached" Boolean false
    "state" Ref "null"
    "expiration" String "2017-10-03T00:00:00"

    "keyId" String "CXC4010623"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010625_1"
    exception none
    nrOfAttributes 9
    moType RcsLM:CapacityKey
    "capacityKeyId" String "CXC4010625_1"
    "licensedCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 18
        "noLimit" Boolean false

    "grantedCapacityLevel" Int32 18
    "licensedCapacityLimitReached" Boolean false
    "state" Ref "null"
    "expiration" String "2017-10-03T00:00:00"

    "keyId" String "CXC4010625"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010071/4002"
    exception none
    nrOfAttributes 8
    moType RcsLM:CapacityState
    "capacityStateId" String "CXC4010071/4002"
    "licenseState" Integer 1 
    "keyId" String "CXC4010071/4002"
    "currentCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 1
        "noLimit" Boolean false
    "grantedCapacityLevel" Int32 "1"
    "licensedCapacityLimitReached" Boolean false
    "serviceState" Integer 1
         1
    "capacityKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,CapacityKey=CXC4010071/4002_1

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010071/4003"
    exception none
    nrOfAttributes 8
    moType RcsLM:CapacityState
    "capacityStateId" String "CXC4010071/4003"
    "licenseState" Integer 1
    "keyId" String "CXC4010071/4003"
    "currentCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 1
        "noLimit" Boolean false
    "grantedCapacityLevel" Int32 "1"
    "licensedCapacityLimitReached" Boolean false
    "serviceState" Integer 1
         1
    "capacityKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,CapacityKey=CXC4010071/4003_1

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010608"
    exception none
    nrOfAttributes 8
    moType RcsLM:CapacityState
    "capacityStateId" String "CXC4010608"
    "licenseState"  Integer 1
    "keyId" String "CXC4010608"
    "currentCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 3500
        "noLimit" Boolean false
    "grantedCapacityLevel" Int32 "3500"
    "licensedCapacityLimitReached" Boolean false
    "serviceState" Integer 1
         1
    "capacityKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,CapacityKey=CXC4010608_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010623"
    exception none
    nrOfAttributes 8
    moType RcsLM:CapacityState
    "capacityStateId" String "CXC4010623"
    "licenseState"  Integer 1

    "keyId" String "CXC4010623"
    "currentCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 65535
        "noLimit" Boolean false
    "grantedCapacityLevel" Int32 "65535"
    "licensedCapacityLimitReached" Boolean false
    "serviceState" Integer 1
    "capacityKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,CapacityKey=CXC4010623_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010625"
    exception none
    nrOfAttributes 8
    moType RcsLM:CapacityState
    "capacityStateId" String "CXC4010625"
    "licenseState"  Integer 1
    "keyId" String "CXC4010625"
    "currentCapacityLimit" Struct
        nrOfElements 2
        "value" Int32 18
        "noLimit" Boolean false
    "grantedCapacityLevel" Int32 "18"
    "licensedCapacityLimitReached" Boolean false
    "serviceState" Integer 1
    "capacityKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,CapacityKey=CXC4010625_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010319_1"
    exception none
    nrOfAttributes 5
    moType RcsLM:FeatureKey
    "featureKeyId" String "CXC4010319_1"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010319"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010320_1"
    exception none
    nrOfAttributes 5
    moType RcsLM:FeatureKey
    "featureKeyId" String "CXC4010320_1"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010320"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010511_1"
    exception none
    nrOfAttributes 5
    moType RcsLM:FeatureKey
    "featureKeyId" String "CXC4010511_1"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010511"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010512_1"
    exception none
    nrOfAttributes 5
    moType RcsLM:FeatureKey
    "featureKeyId" String "CXC4010512_1"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010512"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010513_1"
    exception none
    nrOfAttributes 5
    moType RcsLM:FeatureKey
    "featureKeyId" String "CXC4010513_1"
    "expiration" String "2017-10-03T00:00:00"
    "keyId" String "CXC4010513"
    "productType" String "Baseband"
    "validFrom" String "2016-10-03T00:00:00"
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010319"
    exception none
    nrOfAttributes 4
    moType RcsLM:FeatureState
    "featureStateId" String "CXC4010319"
    "licenseState"  Integer 1
    "keyId" String "CXC4010319"
    "featureKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,FeatureKey=CXC4010319_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010320"
    exception none
    nrOfAttributes 4
    moType RcsLM:FeatureState
    "featureStateId" String "CXC4010320"
    "licenseState"  Integer 1

    "keyId" String "CXC4010320"
    "featureKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,FeatureKey=CXC4010320_1

)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010511"
    exception none
    nrOfAttributes 4
    moType RcsLM:FeatureState
    "featureStateId" String "CXC4010511"
    "licenseState"  Integer 1
    "keyId" String "CXC4010511"
    "featureKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,FeatureKey=CXC4010511_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010512"
    exception none
    nrOfAttributes 4
    moType RcsLM:FeatureState
    "featureStateId" String "CXC4010512"
    "licenseState"  Integer 1
    "keyId" String "CXC4010512"
    "featureKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,FeatureKey=CXC4010512_1
)
CREATE
(
    parent "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1"
    identity "CXC4010513"
    exception none
    nrOfAttributes 4
    moType RcsLM:FeatureState
    "featureStateId" String "CXC4010513"
    "licenseState"  Integer 1 
    "keyId" String "CXC4010513"
    "featureKey" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,Lm=1,FeatureKey=CXC4010512_1
)
SET
(
    mo "ComTop:ManagedElement=$nodeName"
    exception none
    nrOfAttributes 3
    "localDateTime" String "2016-11-29T14:28:30"
    "timeZone" String "UTC"
    "dateTimeOffset" String "+00:00"
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
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSecM:SecM=1,RcsCertM:CertM=1,RcsCertM:CertMCapabilities=1"
    exception none
    nrOfAttributes 1
    "enrollmentSupport" Array Integer 1
         3
)
DELETE
(
  mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1,RcsLM:CapacityKey=1"
)
DELETE
(
  mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsLM:Lm=1,RcsLM:FeatureState=1"
)
SET
(
    mo "ComTop:ManagedElement=$nodeName,ComTop:SystemFunctions=1,RcsSwM:SwM=1,RcsSwM:UpgradePackage=1"
    exception none
    nrOfAttributes 1
    "administrativeData" Array Struct 1
        nrOfElements 6
        "productName" String "Baseband-T"
        "productNumber" String "nodeContainer"
        "productRevision" String "PZ99"
        "productionDate" String "2016-11-28T19:40:09"
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
    "creationTime" String "2016-11-29T11:55:10+00:00"
    "backupName" String "Escalation_default_20161129T115510+0000"
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
    "sequenceNumber" Int32 1000
    "installationTime" String "2016-11-29T11:55:51"
    "productType" String "Baseband"
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
    mo "ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1"
    exception none
    nrOfAttributes 2
    "active" Array Ref 1
        ManagedElement=$nodeName,SystemFunctions=1,SwInventory=1,SwVersion=nodeContainer-PZ99
    "userLabel" String "$nodeName"
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


DEF

cat >> c608.mml << ABC
.open $simName
.select $nodeName
.start
kertayle:file="$Path/$nodeName.mo";
ABC


moFiles+=($nodeName.mo)
done

/netsim/inst/netsim_pipe < c608.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm c608.mml
echo "$0 ended at" $( date +%T );
