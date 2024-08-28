package com.ericsson.simnet.core_automation;

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

public class Get_Protocol {

	public String getProtocol(String neType) {

		if ((neType.toLowerCase().contains("wcdma r"))
				|| (neType.toLowerCase().contains("lte erbs"))
				|| neType.toLowerCase().contains("mgw")
				|| (neType.toLowerCase().contains("rbs")))
			return "IIOP_PROT";
		else if ((neType.toLowerCase().contains("msc"))
				|| ((neType.toLowerCase().contains("bsc")) && (neType
						.toLowerCase().contains("apg43l")))
				|| (neType.toLowerCase().contains("hlr")))
			return "APG_APGTCP";
		else if ((neType.toLowerCase().contains("ml-tn"))
				|| (neType.toLowerCase().contains("ml-cn"))
				|| (neType.toLowerCase().contains("ml 6691"))
				|| (neType.toLowerCase().contains("ml-lh"))
				|| (neType.toLowerCase().contains("prbs")))
			return "SNMP_TELNET_PROT";
		else if ((neType.toLowerCase().contains("juniper mx"))
				|| (neType.toLowerCase().contains("cisco asr900"))
				|| (neType.toLowerCase().contains("cisco asr9000")))
			return "SNMP_SSH_TELNET_PROT";
		else if ((neType.toLowerCase().contains("h2s"))
				|| (neType.toLowerCase().contains("esapv"))
				|| ((neType.toLowerCase().contains("prbs")) && ((neType
						.toLowerCase().contains("ecim")))))
			return "NETCONF_PROT";

		else if ((neType.toLowerCase().contains("epg-ssr"))
				|| (neType.toLowerCase().contains("epg-evr"))
				|| ((neType.toLowerCase().contains("fronthaul")) && (neType
						.toLowerCase().contains("17b")))
				|| (neType.toLowerCase().contains("esapc"))
				|| (neType.toLowerCase().contains("dua-s"))
				|| (neType.toLowerCase().contains("bbsc"))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("hss-fe")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("mtas")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("cscf")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("sbg")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("eme")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("wcg")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("bsp")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("mrsv")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("mrfv")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("upg")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("dsc")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("bsc")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("mrfv")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("wmg")))
				|| ((neType.toLowerCase().contains("core")) && (neType
						.toLowerCase().contains("ipworks")))

		)
			return "NETCONF_PROT_SSH";
		else if ((neType.toLowerCase().contains("lte prbs"))
				|| (neType.toLowerCase().contains("tcu04"))
				|| (neType.toLowerCase().contains("spitfire"))
				|| ((neType.toLowerCase().contains("c608 ")) && (neType
						.toLowerCase().contains("ecim"))))
			return "NETCONF_PROT_TLS";
		// modified a little bit.. if any problem related to SGSN check here if
		// case first
		else if (neType.toLowerCase().contains("sgsn"))
			if ((neType.toLowerCase().contains("sgsn"))
					&& (neType.toLowerCase().contains("cs")))
				return "SGSN_PROT";
			else
				return "SGSN";
		else if (neType.toLowerCase().contains("stn"))
			return "STN_PROT";
		else if ((neType.toLowerCase().contains("sasn"))
				|| (neType.toLowerCase().contains("epg-juniper"))
				|| (neType.toLowerCase().contains("siu02"))
				|| (neType.toLowerCase().contains("tcu02")))
			return "SNMP_SSH_PROT";
		else if ((neType.toLowerCase().contains("router 8801"))
				|| (neType.toLowerCase().contains("fronthaul"))
				|| (neType.toLowerCase().contains("esc"))
				|| (neType.toLowerCase().contains("ssr"))
				|| (neType.toLowerCase().contains("vbng")))
			return "LANSWITCH_PROT";
		else if (neType.toLowerCase().contains("sapc"))
			return "TSP_PROT";
		else if ((neType.toLowerCase().contains("ccn"))
				|| (neType.toLowerCase().contains("mtas"))
				|| (neType.toLowerCase().contains("cscf"))
				|| (neType.toLowerCase().contains("sapc"))
				|| (neType.toLowerCase().contains("hss-fe-tsp"))
				|| (neType.toLowerCase().contains("vpn")))
			return "TSP_SSH_PROT";
		else if (neType.toLowerCase().contains("ecm"))
			return "HTTP_HTTPS_PORT";
		// modified a little bit any problem related to APG_BSC_43L change the
		// code with reference to decision module in simdeb code

		else if ((neType.toLowerCase().contains("ml-pt"))
				|| (neType.toLowerCase().contains("ml 6352"))
				|| (neType.toLowerCase().contains("ml 6351"))
				|| (neType.toLowerCase().contains("ml 6391")))
			return "MLPT_PORT";
		else
			return "NO_PROT";

	}
}
