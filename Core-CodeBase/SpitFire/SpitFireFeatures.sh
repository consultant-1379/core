#!/bin/bash
########################################################################
# Version     : 16.16
# Revision    :
# Purpose     : Loads features for SpitFire nodes
# Description : Creates Mos and sets attributes
# Date        : Oct 2016
# Who         : xgouhar
########################################################################
########################################################################
#Script Usage#
########################################################################
usage (){

echo "Usage  : $0 <sim name> "

echo "Example: $0 CORE-ST-SpitFire-16A-V12x10"

}
neType(){

echo "ERROR: The script runs only SpitFite nodes"
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
#To check the if simname is Spitfire or Router versions#
########################################################################
if [ $simName != *"SpitFire"* || $simName != *"R6274"* || $simName != *"R6675"* || $simName == *"R6371"* || $simName == *"R6471-1"* || $simName == *"R6471-2"* ]
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
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IprBrM:BrM=1"
    identity "1"
    moType IprBrM:BrmBackupManager
    exception none
    nrOfAttributes 4
    "backupDomain" String "System"
    "backupType" String "Configuration"
    "brmBackupManagerId" String "1"
    "progressReport" Struct
        nrOfElements 11
        "actionName" String "action"
        "additionalInfo" Array String 1
        "N/A"
        "progressInfo" String "N/A"
        "progressPercentage" Uint8 0
        "result" Integer 3
        "resultInfo" String "Not Available"
        "state" Integer 3
        "actionId" Uint16 0
        "timeActionStarted" String "1970-01-01T00:01:46UTC"
        "timeActionCompleted" String "1970-01-01T00:01:46UTC"
        "timeOfLastStatusUpdate" String "1970-01-01T00:01:46UTC"

)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IprBrM:BrM=1,IprBrM:BrmBackupManager=1"
    identity "1"
    moType IprBrM:BrmBackup
    exception none
    nrOfAttributes 7
    "backupName" String "Configuration_1970-01-01T05-05-34"
    "brmBackupId" String "1"
    "creationTime" String "1970-01-01T05:05:34UTC"
    "creationType" Integer 1
    "progressReport" Struct
        nrOfElements 11
        "actionName" String "action"
        "additionalInfo" Array String 1
        "N/A"
        "progressInfo" String "N/A"
        "progressPercentage" Uint8 0
        "result" Integer 1
        "resultInfo" String "Not Available"
        "state" Integer 3
        "actionId" Uint16 0
        "timeActionStarted" String "1970-01-01T00:01:46UTC"
        "timeActionCompleted" String "1970-01-01T00:01:46UTC"
        "timeOfLastStatusUpdate" String "1970-01-01T00:01:46UTC"

    "status" Integer 1
    "swVersion" Array Struct 1
        nrOfElements 6
        "productName" String "IPOS"
        "productNumber" String "CXC1737666_1"
        "productRevision" String "CXP9027695_1-R1C774_536"
        "productionDate" String "2015-11-12T10:14:14Z"
        "description" String "Copyright (C) 1998-2015, Ericsson AB. All rights reserved.Image compiled with linux kernel version: .Image running with linux kernel version: 3.14.37-mvista"
        "type" String "Release"

)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IprBrM:BrM=1,IprBrM:BrmBackupManager=1"
    identity "2"
    moType IprBrM:BrmBackup
    exception none
    nrOfAttributes 7
    "backupName" String "Configuration_1970-01-01T02-48-22"
    "brmBackupId" String "2"
    "creationTime" String "1970-01-01T02:48:22UTC"
    "creationType" Integer 1
    "progressReport" Struct
        nrOfElements 11
        "actionName" String "action"
        "additionalInfo" Array String 1
        "N/A"
        "progressInfo" String "N/A"
        "progressPercentage" Uint8 0
        "result" Integer 3
        "resultInfo" String "Not Available"
        "state" Integer 3
        "actionId" Uint16 0
        "timeActionStarted" String "1970-01-01T00:01:49UTC"
        "timeActionCompleted" String "1970-01-01T00:01:49UTC"
        "timeOfLastStatusUpdate" String "1970-01-01T00:01:49UTC"

    "status" Integer 1
    "swVersion" Array Struct 1
        nrOfElements 6
        "productName" String "IPOS"
        "productNumber" String "CXC1737666_1"
        "productRevision" String "CXP9027695_1-R1C775_540"
        "productionDate" String "2015-11-12T16:46:33Z"
        "description" String "Copyright (C) 1998-2015, Ericsson AB. All rights reserved.Image compiled with linux kernel version: .Image running with linux kernel version: 3.14.37-mvista"
        "type" String "Release"

)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IprBrM:BrM=1,IprBrM:BrmBackupManager=1"
    identity "1"
    moType IprBrM:BrmBackupLabelStore
    exception none
    nrOfAttributes 5
    "brmBackupLabelStoreId" String "1"
    "lastCreatedBackup" String "Configuration_1970-01-01T02-48-22"
    "lastExportedBackup" String "null"
    "lastImportedBackup" String "null"
    "lastRestoredBackup" String "null"
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "p01_RP1_SPR2-${productRevision}-Release"
    moType IPR_SwIM:SwVersion
    exception none
    nrOfAttributes 6
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Router6672"
        "productNumber" String "${productNumber}"
        "productRevision" String "${productRevision}"
        "productionDate" String "2016-01-22T18:04:30Z"
        "description" String "Copyright (C) 1998-2016, Ericsson AB. All rights reserved.Image compiled with linux kernel version: .Image running with linux kernel version: 3.14.37-mvista"
        "type" String "Release"

    "swVersionId" String "p01_RP1_SPR2-${productRevision}-Release"
    "timeOfActivation" String "2015-06-27T10:03:08Z"
    "timeOfDeactivation" String "null"
    "timeOfInstallation" String "2015-06-27T09:38:53Z"
    "userLabel" String "null"
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "p02_RP1_SPR2-CXP9027695_1-R1C950_1012-Release"
    moType IPR_SwIM:SwVersion
    exception none
    nrOfAttributes 6
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Router6672"
        "productNumber" String "CXP9027695_1"
        "productRevision" String "CXP9027695_1-R1C950_1012"
        "productionDate" String "2015-12-10T07:31:08Z"
        "description" String "Copyright (C) 1998-2015, Ericsson AB. All rights reserved.Image compiled with linux kernel version: .Image running with linux kernel version: 3.14.37-mvista"
        "type" String "Release"

    "swVersionId" String "p02_RP1_SPR2-CXP9027695_1-R1C950_1012-Release"
    "timeOfActivation" String "null"
    "timeOfDeactivation" String "null"
    "timeOfInstallation" String "2015-06-27T12:39:50Z"
    "userLabel" String "null"
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "APP-CXC1738380/1-R1E56_1608"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "APP"
        "productNumber" String "Number"
        "productRevision" String "CXC1738380/1-R1E56_1608"
        "productionDate" String "N/A"
        "description" String "APP"
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "APP-CXC1738380/1-R1E56_1608"
    "userLabel" String "null"
)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "Golden-bootstrap-CXC1738377_1-R1A11(KFFFFIFFFF)"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Golden"
        "productNumber" String "Number"
        "productRevision" String "bootstrap-CXC1738377_1-R1A11(KFFFFIFFFF)"
        "productionDate" String "N/A"
        "description" String "Golden"
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "Golden-bootstrap-CXC1738377_1-R1A11(KFFFFIFFFF)"
    "userLabel" String "null"
)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "Primary-bootstrap-CXC1738377_1-R1A14(K0000I0000)"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Primary"
        "productNumber" String "Number"
        "productRevision" String "bootstrap-CXC1738377_1-R1A14(K0000I0000)"
        "productionDate" String "N/A"
        "description" String "Primary"
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "Primary-bootstrap-CXC1738377_1-R1A14(K0000I0000)"
    "userLabel" String "null"
)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "Golden-SBI-CXC1738587_1-R1A10"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Golden"
        "productNumber" String "Number"
        "productRevision" String "SBI-CXC1738587_1-R1A10"
        "productionDate" String "N/A"
        "description" String "N/A"
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "Golden-SBI-CXC1738587_1-R1A10"
    "userLabel" String "null"
)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "FPGA-CXC1738286/1-R1B0100"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "FPGA"
        "productNumber" String "CXC1737871/1"
        "productRevision" String "R1B0100"
        "productionDate" String "N/A"
        "description" String "FPGA for Spirfire P1M. Copyright (C) 1998-2015, Ericsson AB. All rights reserved."
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "FPGA-CXC1738286/1-R1B0100"
    "userLabel" String "null"
)

CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
    identity "Kernel-Linux-3.14-CXC1738378_1-R1D05(K0000I0000)"
    moType IPR_SwIM:SwItem
    exception none
    nrOfAttributes 5
    "additionalInfo" String "null"
    "administrativeData" Struct
        nrOfElements 6
        "productName" String "Kernel"
        "productNumber" String "Number"
        "productRevision" String "Linux-3.14-CXC1738378_1-R1D05(K0000I0000)"
        "productionDate" String "N/A"
        "description" String "N/A"
        "type" String "Release"

    "consistsOf" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
    "swItemId" String "Kernel-Linux-3.14-CXC1738378_1-R1D05(K0000I0000)"
    "userLabel" String "null"
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1"
     exception none
    nrOfAttributes 1
    "active" Array Ref 1
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1,IPR_SwIM:SwVersion=p01_RP1_SPR2-${productRevision}-Release"
    exception none
    nrOfAttributes 1
     "consistsOf" Array Ref 6
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=APP-CXC1738380/1-R1E56_1608
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Golden-bootstrap-CXC1738377_1-R1A11(KFFFFIFFFF)
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Primary-bootstrap-CXC1738377_1-R1A14(K0000I0000)
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Golden-SBI-CXC1738587_1-R1A10
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=FPGA-CXC1738286/1-R1B0100
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Kernel-Linux-3.14-CXC1738378_1-R1D05(K0000I0000)
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_SwIM:SwInventory=1,IPR_SwIM:SwVersion=p02_RP1_SPR2-CXP9027695_1-R1C950_1012-Release"
    exception none
    nrOfAttributes 1
     "consistsOf" Array Ref 6
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=APP-CXC1738380/1-R1E56_1608
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Golden-bootstrap-CXC1738377_1-R1A11(KFFFFIFFFF)
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Primary-bootstrap-CXC1738377_1-R1A14(K0000I0000)
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Golden-SBI-CXC1738587_1-R1A10
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=FPGA-CXC1738286/1-R1B0100
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwItem=Kernel-Linux-3.14-CXC1738378_1-R1D05(K0000I0000)
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1"
    identity "1"
    moType IPR_HwIM:HwInventory
    exception none
    nrOfAttributes 2
    "hwInventoryId" String "1"
    "timeOfLatestInvChange" String "1970-01-01T00:03:09"
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 14
    "additionalAttributes" Array Struct 4
        nrOfElements 2
        "name" String "EEPROM Id"
        "value" String "32"

        nrOfElements 2
        "name" String "EEPROM Version"
        "value" String "1"

        nrOfElements 2
        "name" String "FPGA Id"
        "value" String "CXC 173 8286/1"

        nrOfElements 2
        "name" String "FPGA Revision"
        "value" String "R1A11"

    "dateOfLastService" String "2015-09-10T00:00:00"
    "dateOfManufacture" String "2015-09-10T00:00:00"
    "equipmentMoRef" Array Ref 1
        ManagedElement=1,Equipment=1,Shelf=1,Slot=1,Card=1
    "hwItemId" String "1"
    "hwModel" String "lc-1-10ge-20-8-port"
    "hwName" String "LC"
    "hwType" String "Card"
    "hwUnitLocation" String "slot:0"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "LC"
        "productNumber" String "BFD101131/2"
        "productRevision" String "P1D"
        "productionDate" String "2015-09-10T00:00:00"
        "description" String "N/A"
        "type" String "Card"

    "serialNumber" String "D821781392"
    "swInvMoRef" Array Ref 0

)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/1"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "SX / MM"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "850.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "300"

        nrOfElements 2
        "name" String "CLEI Code"
        "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"


    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/1"
    "hwModel" String "SX / MM"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:0"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FTLF8519P3BNL-E9A"
        "productRevision" String "A"
         "productionDate" String ""
         "description" String ""
        "type" String "Transceiver"


    "serialNumber" String "NSL0E5P"
    "swInvMoRef" Array Ref 0

)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/12"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "SX / MM"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "850.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "300"

        nrOfElements 2
        "name" String "CLEI Code"
        "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"



    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/12"
    "hwModel" String "SX / MM"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:11"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FTLF8519P3BNL-E9A"
        "productRevision" String "A"
        "productionDate" String ""
        "description" String ""
        "type" String "Transceiver"
    "serialNumber" String "NSH24X9"
    "swInvMoRef" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/2"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "T / Cat5"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "0.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "100"

        nrOfElements 2
        "name" String "CLEI Code"
         "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"

    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/2"
    "hwModel" String "T / Cat5"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:1"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FCLF-8521-3-ER  A"
        "productRevision" String "A"
        "productionDate" String ""
        "description" String ""
        "type" String "Transceiver"

    "serialNumber" String "PT22SA5"
    "swInvMoRef" Array Ref 0
  )
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/3"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "T / Cat5"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "0.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "100"

        nrOfElements 2
        "name" String "CLEI Code"
        "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"

    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/3"
    "hwModel" String "T / Cat5"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:2"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FCLF-8521-3-ER  A"
        "productRevision" String "A"
          "productionDate" String ""
        "description" String ""
         "type" String "Transceiver"

    "serialNumber" String "PT22SX1"
    "swInvMoRef" Array Ref 0

)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/4"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "T / Cat5"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "0.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "100"

        nrOfElements 2
        "name" String "CLEI Code"
         "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"

    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/4"
    "hwModel" String "T / Cat5"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:3"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FCLF-8521-3-ER  A"
        "productRevision" String "A"
         "productionDate" String ""
        "description" String ""
        "type" String "Transceiver"

	"serialNumber" String "PT22SGN"
    "swInvMoRef" Array Ref 0
 )
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/5"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "T / Cat5"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "0.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "100"

        nrOfElements 2
        "name" String "CLEI Code"
        "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"
    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/5"
    "hwModel" String "T / Cat5"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:4"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FCLF-8521-3-ER  A"
        "productRevision" String "A"
        "productionDate" String ""
        "description" String ""
        "type" String "Transceiver"

    "serialNumber" String "PT22SKD"
    "swInvMoRef" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "1/7"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 12
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "Media Type"
        "value" String "T / Cat5"

        nrOfElements 2
        "name" String "Wavelength"
        "value" String "0.00"

        nrOfElements 2
        "name" String "Distance"
        "value" String "100"

        nrOfElements 2
        "name" String "CLEI Code"
         "value" String "N/A"

        nrOfElements 2
        "name" String "Ericsson Approved"
        "value" String "Yes"

    "equipmentMoRef" Array Ref 0
    "hwItemId" String "1/7"
    "hwModel" String "T / Cat5"
    "hwName" String "SFP"
    "hwType" String "Transceiver"
    "hwUnitLocation" String "slot:0/port:6"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "SFP"
        "productNumber" String "FCLF-8521-3-ER  A"
        "productRevision" String "A"
        "productionDate" String ""
        "description" String ""
        "type" String "Transceiver"

    "serialNumber" String "PSK1EFQ"
    "swInvMoRef" Array Ref 0

)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "2"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 14
    "additionalAttributes" Array Struct 4
        nrOfElements 2
        "name" String "EEPROM Version"
        "value" String "1"

        nrOfElements 2
        "name" String "EEPROM Id"
        "value" String "32"

        nrOfElements 2
        "name" String "FPGA Id"
        "value" String "CXC 173 8286/1"

        nrOfElements 2
        "name" String "FPGA Revision"
        "value" String "R1A11"

    "dateOfLastService" String "2015-09-10T00:00:00"
    "dateOfManufacture" String "2015-09-10T00:00:00"
    "equipmentMoRef" Array Ref 1
        ManagedElement=1,Equipment=1,Shelf=1,Slot=1,Card=1
    "hwItemId" String "2"
    "hwModel" String "rpsw"
    "hwName" String "RPSW"
    "hwType" String "Card"
    "hwUnitLocation" String "slot:1"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "RPSW"
        "productNumber" String "BFD101131/2"
        "productRevision" String "P1D"
        "productionDate" String "2015-09-10T00:00:00"
        "description" String "N/A"
        "type" String "Card"


    "serialNumber" String "D821781392"
    "swInvMoRef" Array Ref 2
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p01_RP1_SPR2-${productRevision}-Release
        ManagedElement=1,SystemFunctions=1,SwInventory=1,SwVersion=p02_RP1_SPR2-CXP9027695_1-R1C950_1012-Release

)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "3"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 14
    "additionalAttributes" Array Struct 2
        nrOfElements 2
        "name" String "EEPROM Id"
        "value" String "32"

        nrOfElements 2
        "name" String "EEPROM Version"
        "value" String "1"

    "dateOfLastService" String "2015-08-10T00:00:00"
    "dateOfManufacture" String "2015-08-10T00:00:00"
    "equipmentMoRef" Array Ref 1
        ManagedElement=1,Equipment=1,Shelf=1,Slot=1,Card=1
    "hwItemId" String "3"
    "hwModel" String "pm-dc"
    "hwName" String "PM"
    "hwType" String "Card"
    "hwUnitLocation" String "slot:2"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "PM"
        "productNumber" String "BMR91182/1"
        "productRevision" String "P1B"
        "productionDate" String "2015-08-10T00:00:00"
         "description" String "N/A"
        "type" String "Card"

	"serialNumber" String "BR84166982"
    "swInvMoRef" Array Ref 0
)
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "4"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 14
    "additionalAttributes" Array Struct 2
        nrOfElements 2
        "name" String "EEPROM Id"
        "value" String "32"

        nrOfElements 2
        "name" String "EEPROM Version"
        "value" String "1"

    "additionalInformation" String "2015-08-07T00:00:00"
    "dateOfLastService" String "2015-08-07T00:00:00"

    "equipmentMoRef" Array Ref 1
        ManagedElement=1,Equipment=1,Shelf=1,Slot=1,Card=1
    "hwItemId" String "4"
    "hwModel" String "fan"
    "hwName" String "FT"
    "hwType" String "Card"
    "hwUnitLocation" String "slot:3"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "FT"
        "productNumber" String "BKV106189/1"
        "productRevision" String "P1A"
        "productionDate" String "2015-08-07T00:00:00"
         "description" String "N/A"
        "type" String "Card"

    "serialNumber" String "SBR32002507 "
    "swInvMoRef" Array Ref 0
  )
CREATE
(
    parent "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,IPR_HwIM:HwInventory=1"
    identity "5"
    moType IPR_HwIM:HwItem
    exception none
    nrOfAttributes 14
    "additionalAttributes" Array Struct 5
        nrOfElements 2
        "name" String "EEPROM Id"
        "value" String "32"

        nrOfElements 2
        "name" String "EEPROM Version"
        "value" String "1"

        nrOfElements 2
        "name" String "Chassis Type"
        "value" String "SF P1S"

        nrOfElements 2
        "name" String "MAC Address Start"
        "value" String "A4:A1:C2:DD:75:80"

        nrOfElements 2
        "name" String "MAC Address Count"
        "value" String "64"

    "additionalInformation" String "2015-09-10T00:00:00"
    "dateOfLastService" String "2015-09-10T00:00:00"

    "equipmentMoRef" Array Ref 0
    "hwItemId" String "5"
    "hwModel" String "backplane"
    "hwName" String "BP"
    "hwType" String "Card"
    "hwUnitLocation" String "slot:4"
    "licMgmtMoRef" Array Ref 0
    "manualDataEntry" Integer 1
    "productData" Struct
        nrOfElements 6
        "productName" String "BP"
        "productNumber" String "ROA1286101"
        "productRevision" String "N/A"
        "productionDate" String "2015-09-10T00:00:00"
        "description" String "N/A"
        "type" String "Card"

    "serialNumber" String "N/A"
    "swInvMoRef" Array Ref 0
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=1"
    exception none
    nrOfAttributes 1
    "schemaId" String "ComFm"
)

SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=2"
    exception none
    nrOfAttributes 1
    "schemaId" String "ComSecM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=3"
    exception none
    nrOfAttributes 1
    "schemaId" String "ComSysM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=4"
    exception none
    nrOfAttributes 1
    "schemaId" String "ComTop"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=5"
    exception none
    nrOfAttributes 1
    "schemaId" String "ECIM_CommonLibrary"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=6"
    exception none
    nrOfAttributes 1
    "schemaId" String "Ipos_Pm"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=7"
    exception none
    nrOfAttributes 1
    "schemaId" String "IprBrM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=8"
    exception none
    nrOfAttributes 1
    "schemaId" String "IPR_HwIM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=9"
    exception none
    nrOfAttributes 1
    "schemaId" String "IPR_SwIM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=11"
    exception none
    nrOfAttributes 1
    "schemaId" String "Lm_MOM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=10"
    exception none
    nrOfAttributes 1
    "schemaId" String "IprSwM"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=13"
    exception none
    nrOfAttributes 1
    "schemaId" String "Router6000Equipment"

)
 SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:Schema=12"
    exception none
    nrOfAttributes 1
    "schemaId" String "Router6000CertM"

 )
SET
(
    mo "ManagedElement=1,SystemFunctions=1,Pm=1,PmMeasurementCapabilities=1"
    exception none
    nrOfAttributes 1
    "fileLocation" String "/c/pm_data"
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1"
    exception none
    nrOfAttributes 4
    "lmState" Integer 1
    "lastLicenseInventoryRefresh" String "1970-01-01T04:32:21"
    "fingerprintUpdateable" Boolean false
    "fingerprint" String "D821778121"
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:EmergencyUnlock=1"
    exception none
    nrOfAttributes 1
    "activationsLeft" Uint8 2
)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:KeyFileManagement=1"
    exception none
    nrOfAttributes 1
    "reportProgress" Struct
        nrOfElements 11
        "actionName" String "action"
        "additionalInfo" Array String 1
        "N/A"
        "progressInfo" String ""
        "progressPercentage" Uint8 0
        "result" Integer 3
        "resultInfo" String "N/A"
        "state" Integer 3
        "actionId" Uint16 0
        "timeActionStarted" String "NULL"
        "timeActionCompleted" String "NULL"
        "timeOfLastStatusUpdate" String "NULL"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:CapacityKey=1"
    exception none
    nrOfAttributes 1
    "productType" String "Router6000"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:FeatureKey=1"
    exception none
    nrOfAttributes 1
    "productType" String "Router6000"

)
SET
(
    mo "ComTop:ManagedElement=1,ComTop:SystemFunctions=1,Lm_MOM:Lm=1,Lm_MOM:KeyFileManagement=1,Lm_MOM:KeyFileInformation=1"
    exception none
    nrOfAttributes 1
    "productType" String "Router6000"

)
SET
(
    mo "ManagedElement=1,SystemFunctions=1,SecM=1,CertM=1,CertMCapabilities=1"
    exception none
    nrOfAttributes 2
    "fingerprintSupport" Integer 2
    "enrollmentSupport" Array Integer 2
         0
         3
)
SET
(
    mo "ManagedElement=1,SystemFunctions=1,SecM=1,Tls=1"
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
cat >> spit.mml << ABC
.open $simName
.select $nodeName
.start
setmoattribute:mo="1",attributes="managedElementId(string)=1";
setmoattribute:mo="ManagedElement=1",attributes="dateTimeOffset=+01:00";
kertayle:file="$Path/$nodeName.mo";
set:varbinds="sysName.0=\\"$nodeName\\"";
pmdata:disable;
ABC

moFiles+=($nodeName.mo)
cd /netsim/netsim_dbdir/simdir/netsim/netsimdir/${simName}/${nodeName}/fs
mkdir md
cd $Path
done

/netsim/inst/netsim_pipe < spit.mml
for filenum in ${moFiles[@]}
do
rm $filenum
done
rm spit.mml
echo "$0 ended at" $( date +%T );

