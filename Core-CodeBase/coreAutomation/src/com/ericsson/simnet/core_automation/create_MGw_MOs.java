package com.ericsson.simnet.core_automation;

import java.io.*;

/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

public class create_MGw_MOs {

	public void MOs_generation(int No_of_MOs_Req)
	// public void main(String[] args)
	{
		// int No_of_MOs_Req=100000;
		int Identity = 123456;
		if (No_of_MOs_Req <= 1780) {
			call_Mand_MOs_MGw();
			Identity = call_250(No_of_MOs_Req - 30, Identity);
		} else if (No_of_MOs_Req <= 9280) {
			call_Mand_MOs_MGw();
			Identity = call_250(1750, Identity);
			Identity = call_500(No_of_MOs_Req - 1780, Identity);
		}

		else if (No_of_MOs_Req < 29280) {
			call_Mand_MOs_MGw();
			Identity = call_250(1750, Identity);
			Identity = call_500(7500, Identity);
			Identity = call_1000(No_of_MOs_Req - 9280, Identity);
		} else if (No_of_MOs_Req < 600000) {
			call_Mand_MOs_MGw();
			Identity = call_250(1750, Identity);
			Identity = call_500(7500, Identity);
			Identity = call_1000(20000, Identity);
			Identity = call_30000(No_of_MOs_Req - 29280, Identity);
		} else {
			System.out
					.println("Sorry we dont have support for more than 600000 MOs");
			System.out
					.println("Please contact Automation Team to increase the support");
		}
	}

	public void call_Mand_MOs_MGw() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mo", true)));

			// creating mandatory attribute "loadModuleRef" for system must
			// create mo which is not created "XpProgram"
			System.out
					.println("INFO: creating Mandatory mo \"XpProgram\" which is not created during .mogenerator generate");
			out.println("CREATE");
			out.println("(");
			out.println("parent \"ManagedElement=1,Equipment=1,Macu=1\"");
			out.println("identity 1");
			out.println("moType XpProgram");
			out.println("exception none");
			out.println("\"loadModuleRef\" Ref \"null\"");
			out.println(")");

			// creating mandatory attribute "bgfNetworkMoRef" for system must
			// create mo which is not created "BgfNetworkAssociation"
			System.out
					.println("INFO: creating Mandatory mo \"BgfNetworkAssociation\" which is not created during .mogenerator generate");
			out.println("CREATE");
			out.println("(");
			out.println("parent \"ManagedElement=1,BorderGatewayFunction=1,BgfApplication=1\"");
			out.println("identity 1");
			out.println("moType BgfNetworkAssociation");
			out.println("exception none");
			out.println("\"bgfNetworkMoRef\" Ref \"null\"");
			out.println(")");

			// creating mandatory attribute "VppEthernetInterface" for system
			// must create mo which is not created "VppEthernetInterface"
			System.out
					.println("INFO: creating Mandatory mo \"VppEthernetInterface\" which is not created during .mogenerator generate");
			for (int i = 0; i < 28; i++) {
				out.println("CREATE");
				out.println("(");
				out.println("parent \"ManagedElement=1,Equipment=1,Subrack=1,Slot="
						+ (i + 1) + ",PlugInUnit=1,VppUnit=1\"");
				out.println("identity 1");
				out.println("moType VppEthernetInterface");
				out.println("exception none");
				out.println("\"portLocation\" Integer 0");
				out.println(")");

			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int call_250(int No_of_MOs_Req, int Identity) {
		int count, diff = No_of_MOs_Req;
		if (No_of_MOs_Req == 1750)
			count = 250;
		else
			count = (No_of_MOs_Req / 7) + 1;

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mo", true)));
			// EthernetSwitchFabric_to_Vlan ----max--250
			// got

			// System.out.println("count"+count);
			// System.out.println("diff initial"+diff);
			// System.out.println("entered 250 cardinality for loop");
			for (int i = 0; i < count; i++) {

				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,EthernetSwitchFabric=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Vlan");
					out.println("exception none");
					out.println("\"vid\" Integer 1");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2ProfileChina -----max --250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2ProfileChina");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2ProfileAnsi ---------max---250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2ProfileAnsi");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}

			// TransportNetwork_to_Mtp2ProfileTtc ----max--250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2ProfileTtc");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3ua_to_M3uaLocalSp ----max---250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaLocalSp");
					out.println("exception none");
					out.println("\"sctpRef\" Ref \"null\"");
					out.println("\"spType\" Integer 1");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3uaRk_to_M3uaRkGrouping ---------250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1,M3uaRk=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaRkGrouping");
					out.println("exception none");
					out.println("\"dpc\" Integer 1");
					out.println("\"opc\" Integer 0");
					out.println("\"serviceInd\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// Mtp3bSpTtc_to_Mtp3bSls -------250
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,Mtp3bSpTtc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp3bSls");
					out.println("exception none");
					out.println("\"mtp3bSrsId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Identity;
	}

	public int call_500(int No_of_MOs_Req, int Identity) {
		int count, diff = No_of_MOs_Req;
		if (No_of_MOs_Req == 7500)
			count = 500;
		else
			count = (No_of_MOs_Req / 15) + 1;

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mo", true)));
			// Aal2Sp_to_Aal2Ap --max-800 restr to 500

			// System.out.println("count"+count);
			// System.out.println("diff initial"+diff);
			// System.out.println("entered 500 cardinality for loop");

			for (int i = 0; i < count; i++) {

				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,Aal2Sp=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Aal2Ap");
					out.println("exception none");
					out.println("\"sigLinkId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_AtmPort ----800
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType AtmPort");
					out.println("exception none");
					out.println("\"uses\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_AtmTrafficDescriptor
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType AtmTrafficDescriptor");
					out.println("exception none");
					out.println("\"ingressAtmQos\" Integer 1");
					out.println("\"egressAtmQos\" Integer 1");
					out.println("\"serviceCategory\" Integer 1");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_ImaGroup -----500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ImaGroup");
					out.println("exception none");
					out.println("\"physicalPortList\" Array Ref 0");

					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// Ip_to_IpAtmLink --------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,IpOam=1,Ip=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType IpAtmLink");
					out.println("exception none");
					out.println("\"aal5TpVccTpId\" Ref \"null\"");
					out.println("\"ipAddress\" String \"\"");
					out.println("\"subnetMask\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// AccessSignalling_to_DChannelTp ------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,AccessSignalling=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType DChannelTp");
					out.println("exception none");
					out.println("\"ds0BundleMoRef\" Ref \"null\"");
					out.println("\"interfaceIdentifier\" Long 0");
					out.println("\"lapdMoRef\" Ref \"null\"");
					out.println("\"lapdSapProfileMoRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// Mtp3bSpTtc_to_Mtp3bAp -------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,Mtp3bSpTtc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp3bAp");
					out.println("exception none");
					out.println("\"routeSetId\" Ref \"null\"");
					out.println("\"serviceInd\" Integer 3");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2TpItu ----500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2TpItu");
					out.println("exception none");
					out.println("\"ds0BundleId\" Ref \"null\"");
					out.println("\"mtp2ProfileItuId\" Ref \"null\"");
					out.println("\"plugInUnitId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2TpChina
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2TpChina");
					out.println("exception none");
					out.println("\"ds0BundleId\" Ref \"null\"");
					out.println("\"mtp2ProfileChinaId\" Ref \"null\"");
					out.println("\"plugInUnitId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2TpAnsi -----500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2TpAnsi");
					out.println("exception none");
					out.println("\"ds0BundleId\" Ref \"null\"");
					out.println("\"mtp2ProfileAnsiId\" Ref \"null\"");
					out.println("\"plugInUnitId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mtp2TpTtc --------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp2TpTtc");
					out.println("exception none");
					out.println("\"ds0BundleId\" Ref \"null\"");
					out.println("\"mtp2ProfileTtcId\" Ref \"null\"");
					out.println("\"plugInUnitId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3ua_to_M3uaRemoteSctpEp -------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaRemoteSctpEp");
					out.println("exception none");
					out.println("\"remoteIpAddress1\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// Mtp3bSpTtc_to_M3uAssociation --------500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,Mtp3bSpTtc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uAssociation");
					out.println("exception none");
					out.println("\"dscp\" Integer 0");
					out.println("\"mtp3bSrsId\" Ref \"null\"");
					out.println("\"remoteIpAddress1\" String \"\"");
					out.println("\"sctpId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// InteractiveMessaging_to_ImVariableMessage ------ 500
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,InteractiveMessaging=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ImVariableMessage");
					out.println("exception none");
					out.println("\"algorithmFileId\" String \"\"");
					out.println("\"algorithmFilePath\" String \"\"");
					out.println("\"duration\" Integer 0");
					out.println("\"fileName\" String \"\"");
					out.println("\"filePath\" String \"\"");
					out.println("\"iteration\" Integer 0");
					out.println("\"messageId\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// Mtp3bSpTtc_to_Mtp3bSrs ----500
			for (int i = 0; i < (count); i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,Mtp3bSpTtc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mtp3bSrs");
					out.println("exception none");
					out.println("\"destPointCode\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Identity;
	}

	public int call_1000(int No_of_MOs_Req, int Identity) {
		int count, diff = No_of_MOs_Req;
		if (No_of_MOs_Req == 20000)
			count = 1000;
		else
			count = (No_of_MOs_Req / 20) + 1;

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mo", true)));
			// BorderGatewayFunction_to_BgfVlan -----1000

			// System.out.println("count"+count);
			// System.out.println("diff initial"+diff);
			// System.out.println("entered 1000 cardinality for loop");

			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,BorderGatewayFunction=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType BgfVlan");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}

			// BorderGatewayFunction_to_BgfNetwork ----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,BorderGatewayFunction=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType BgfNetwork");
					out.println("exception none");
					out.println("\"bgfVlanMoRef\" Ref \"null\"");
					out.println("\"maxAllowedIncomingBw\" Integer 0");
					out.println("\"maxAllowedOutgoingBw\" Integer 0");
					out.println("\"networkName\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// IpNetwork_to_RemoteSite ------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,MgwApplication=1,IpNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RemoteSite");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// InteractiveMessaging_to_ImMessageComposition -----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,InteractiveMessaging=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ImMessageComposition");
					out.println("exception none");
					out.println("\"duration\" Integer 0");
					out.println("\"iteration\" Integer 0");
					out.println("\"listOfMessages\" Array Ref 0");
					out.println("\"messageId\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SccpScrc_to_SccpPolicing ----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,SccpSp=1,SccpScrc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType SccpPolicing");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3ua_to_M3uaRemoteAs ------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaRemoteAs");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3ua_to_M3uaRemoteSp -------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaRemoteSp");
					out.println("exception none");
					out.println("\"m3uaLocalSpRef\" Ref \"null\"");
					out.println("\"m3uaRemoteSctpEpRef\" Ref \"null\"");
					out.println("\"spType\" Integer 1");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// M3ua_to_M3uaRk --------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,M3ua=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType M3uaRk");
					out.println("exception none");
					out.println("\"m3uaRemoteAsRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_TdmCrossConnection -------1000
			// got
			for (int i = 0; i < count; i++) {

				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType TdmCrossConnection");
					out.println("exception none");
					out.println("\"ds0BundleMoRefA\" Ref \"null\"");
					out.println("\"ds0BundleMoRefB\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Aal1TpVccTp ----1000
			for (int i = 0; i < (count - 1); i++) {

				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Aal1TpVccTp");
					out.println("exception none");
					out.println("\"ds0BundleId\" Ref \"null\"");
					out.println("\"continuityCheck\" Boolean true");
					out.println("\"vclTpId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Aal2PathVccTp -----------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Aal2PathVccTp");
					out.println("exception none");
					out.println("\"aal2QoSProfileId\" Ref \"null\"");
					out.println("\"aal2PathId\" Integer 1");
					out.println("\"aal2PathOwner\" Boolean true");
					out.println("\"continuityCheck\" Boolean true");
					out.println("\"vclTpId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Aal2RoutingCase ---1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Aal2RoutingCase");
					out.println("exception none");
					out.println("\"numberDirection\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// InteractiveMessaging_to_ImMultiLanguageMessage -----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,InteractiveMessaging=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ImMultiLanguageMessage");
					out.println("exception none");
					out.println("\"languageVariants\" Array Ref 0");
					out.println("\"messageId\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// InteractiveMessaging_to_ImBasicMessage -----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,InteractiveMessaging=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ImBasicMessage");
					out.println("exception none");
					out.println("\"duration\" Integer 0");
					out.println("\"fileName\" String \"\"");
					out.println("\"filePath\" String \"\"");
					out.println("\"imStreamer\" Ref \"null\"");
					out.println("\"iteration\" Integer 0");
					out.println("\"messageId\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// IpSystem_to_IpAccessHostEt ---1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,IpSystem=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType IpAccessHostEt");
					out.println("exception none");
					out.println("\"ipAddress\" String \"\"");
					out.println("\"ipInterfaceMoRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SccpScrc_to_SccpEntitySet ----1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,SccpSp=1,SccpScrc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType SccpEntitySet");
					out.println("exception none");
					out.println("\"routeIds\" Array Ref 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SccpScrc_to_SccpGlobalTitle ---1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,SccpSp=1,SccpScrc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType SccpGlobalTitle");
					out.println("exception none");
					out.println("\"addressInformation\" String \"\"");
					out.println("\"gtIndicator\" Integer 1");
					out.println("\"translationType\" Integer 0");
					out.println("\"natureOfAddress\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// VpcTp_to_VclTp ------1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,AtmPort=1,VplTp=1,VpcTp=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType VclTp");
					out.println("exception none");
					out.println("\"atmTrafficDescriptorId\" Ref \"null\"");
					out.println("\"externalVci\" Integer 32");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Aal5TpVccTp ---1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Aal5TpVccTp");
					out.println("exception none");
					out.println("\"vclTpId\" Ref \"null\"");
					out.println("\"fromUserMaxSduSize\" Integer 1");
					out.println("\"toUserMaxSduSize\" Integer 1");
					out.println("\"continuityCheck\" Boolean true");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_AtmCrossConnection ---1000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType AtmCrossConnection");
					out.println("exception none");
					out.println("\"vclTpAId\" Ref \"null\"");
					out.println("\"vclTpBId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Identity;
	}

	public int call_30000(int No_of_MOs_Req, int Identity) {
		int count = 0, diff = No_of_MOs_Req;
		count = ((No_of_MOs_Req / 20) + 1);

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mo", true)));
			// AtmPort_to_VplTp -----30000

			// System.out.println("count"+count);
			// System.out.println("diff initial"+diff);
			// System.out.println("entered 30000 cardinality for loop");

			for (int i = 0; i < count; i++) {// 3590
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1,AtmPort=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType VplTp");
					out.println("exception none");
					out.println("\"atmTrafficDescriptor\" Ref \"null\"");
					out.println("\"externalVpi\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// PlugInUnit_to_VlanPort -----30000
			for (int i = 0; i < count; i++) {// 3699

				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,Equipment=1,Subrack=1,Slot=1,PlugInUnit=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType VlanPort");
					out.println("exception none");
					out.println("\"portRef\" Ref \"null\"");
					out.println("\"vid\" Integer 1");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// IpSystem_to_Ipv6Interface ------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,IpSystem=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Ipv6Interface");
					out.println("exception none");
					out.println("\"ethernetRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SignallingProxy_to_MscPoolProxy -----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SignallingProxy=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType MscPoolProxy");
					out.println("exception none");
					out.println("\"nriLength\" Integer 1");
					out.println("\"rpuMoRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// MscPoolProxy_to_RemoteMsc -----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SignallingProxy=1,MscPoolProxy=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RemoteMsc");
					out.println("exception none");
					out.println("\"nriValueList\" Array Integer 0");
					out.println("\"signallingPointCode\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// MscPoolProxy_to_RemoteBsc ----30000
			for (int i = 0; i < (count / 2); i++) {
				if (diff > 0) {// 3590
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SignallingProxy=1,MscPoolProxy=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RemoteBsc");
					out.println("exception none");
					out.println("\"signallingPointCode\" Integer 0");
					out.println("\"vBscLocalSccpEndPoint\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff = diff - 2;
				} else
					break;
			}
			// RemoteBsc_to_CicRangeList -----30000
			for (int i = 0; i < count; i++) {// 7177
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SignallingProxy=1,MscPoolProxy=1,RemoteBsc=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType CicRangeList");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// MscPoolProxy_to_RemoteRnc ----30000
			for (int i = 0; i < count; i++) {// 3590
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SignallingProxy=1,MscPoolProxy=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RemoteRnc");
					out.println("exception none");
					out.println("\"signallingPointCode\" Integer 0");
					out.println("\"vRncLocalSccpEndPoint\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// ResourceAccessContainer_to_ResourceAccess ----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3590
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,MsProcessing=1,ResourceAccessContainer=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ResourceAccess");
					out.println("exception none");
					out.println("\"raType\" Integer 1");
					out.println("\"rpuId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// MsProcessing_to_RouteParameterGroup -----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 2
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,MsProcessing=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RouteParameterGroup");
					out.println("exception none");
					out.println("\"routeParameterGrpId\" Integer 0");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// MsProcessing_to_MsDevicePool ------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3588
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,MsProcessing=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType MsDevicePool");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// IpTrafficSeparation_to_IpAccessHostPool ------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3588
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,MsProcessing=1,IpEtService=1,IpTrafficSeparation=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType IpAccessHostPool");
					out.println("exception none");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Mspg ------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Mspg");
					out.println("exception none");
					out.println("\"os155SpiStandbyId\" Ref \"null\"");
					out.println("\"os155SpiWorkingId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_MspgExtended -----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType MspgExtended");
					out.println("exception none");
					out.println("\"os155SpiProtectionRef\" Ref \"null\"");
					out.println("\"os155SpiWorkingRef\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// TransportNetwork_to_Sctp -----30000

			for (int i = 0; i < (count / 2); i++) {
				if (diff > 0) {// 7178
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,TransportNetwork=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Sctp");
					out.println("exception none");
					out.println("\"numberOfAssociations\" Integer 1");
					out.println("\"maxUserDataSize\" Integer 1");
					out.println("\"rpuId\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff = diff - 2;
				} else
					break;
			}// ----------------------------------------------check me
			// SwManagement_to_UpgradePackage ------30000
			/*
			 * due to issues with mandatory attribute ftpServerIpAddress
			 * commenting this block for (int i=0;i<count;i++) { if(diff>0) {
			 * out.println("CREATE"); out.println("(");
			 * out.println("parent \"ManagedElement=1,SwManagement=1\"");
			 * out.println("identity \""+Identity+"\"");
			 * out.println("moType UpgradePackage");
			 * out.println("exception none");
			 * out.println("\"ftpServerIpAddress\" String \"\"");
			 * out.println("\"upFilePathOnFtpServer\" String \"\"");
			 * out.println(")"); Identity++; diff--; } else break; }
			 */
			// PlugInUnit_to_Program
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3699
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,Equipment=1,Subrack=1,Slot=1,PlugInUnit=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Program");
					out.println("exception none");
					out.println("\"loadModule\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SwManagement_to_ReliableProgramUniter -------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SwManagement=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType ReliableProgramUniter");
					out.println("exception none");
					out.println("\"reliableProgramLabel\" String \"\"");
					out.println("\"admActiveSlot\" Ref \"null\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SwManagement_to_Repertoire -----30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3589
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SwManagement=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType Repertoire");
					out.println("exception none");
					out.println("\"name\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SwManagement_to_SwAllocation --------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {// 3569
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SwManagement=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType SwAllocation");
					out.println("exception none");
					out.println("\"role\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			// SwManagement_to_RemoteFileServer ------30000
			for (int i = 0; i < count; i++) {
				if (diff > 0) {
					out.println("CREATE");
					out.println("(");
					out.println("parent \"ManagedElement=1,SwManagement=1\"");
					out.println("identity \"" + Identity + "\"");
					out.println("moType RemoteFileServer");
					out.println("exception none");
					out.println("\"serverAddress\" String \"\"");
					out.println("\"username\" String \"\"");
					out.println("\"password\" String \"\"");
					out.println(")");
					Identity++;
					diff--;
				} else
					break;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Identity;
	}
}
