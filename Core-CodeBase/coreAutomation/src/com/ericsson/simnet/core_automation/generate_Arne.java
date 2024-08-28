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
package com.ericsson.simnet.core_automation;

import java.io.*;

public class generate_Arne {

	public void arne_build(String SIM_Name, String var_stat) {

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_arne.mml", true)));
			out.println(".open " + SIM_Name);

			out.println(".selectnocallback network");
			out.println(".arneconfig rootmo ONRM_ROOT_MO ");
			out.println(var_stat);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void arne_stat(String SIM_Name, String[] NE_Names, String NE_Type,
			int No_of_Nodes) {
		String var_stat, Group_Name, Sub_Network;

		if (NE_Type.toLowerCase().contains("mgw")) {
			var_stat = ".createarne R6 "
					+ SIM_Name
					+ " MGW %nename secret IP secure sites no_external_associations no_value ";
			arne_build(SIM_Name, var_stat);
		} else if (NE_Type.toLowerCase().contains("lte prbs")) {
			Sub_Network = "PRBS";
			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " "
					+ Sub_Network
					+ " %nename secret IP secure sites no_external_associations ftp";
			arne_build(SIM_Name, var_stat);
		} else if ((NE_Type.toLowerCase().contains("stn"))) {
			String var = NE_Names[0];
			Group_Name = var.substring(0, var.indexOf("0"));
			String var_group = null;
			for (int i = 0; i < No_of_Nodes; i++) {
				if (var_group == null) {
					var_group = NE_Names[i];
				} else {
					var_group = (var_group + "|" + NE_Names[i]);
				}
			}

			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " NETSim %nename secret IP secure sites no_external_associations ftp  STN-SUBNW-1 "
					+ var_group;
			arne_build(SIM_Name, var_stat);
		}
		// correct me i am missing some code
		else if (NE_Type.toLowerCase().contains("rxi")
				|| NE_Type.toLowerCase().contains("rnc")
				|| NE_Type.toLowerCase().contains("rbs")) {
			String var = NE_Names[0];
			Group_Name = var.substring(0, var.indexOf("0"));
			String var_group = null;
			for (int i = 0; i < No_of_Nodes; i++) {
				if (var_group == null) {
					var_group = NE_Names[i];
				} else {
					var_group = (var_group + "|" + NE_Names[i]);
				}
			}

			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " RNC %nename secret IP secure sites no_external_associations ftp defaultgroups";
			arne_build(SIM_Name, var_stat);
		} else if ((NE_Type.toLowerCase().contains("sgsn"))
				&& (NE_Type.toLowerCase().contains("wpp"))) {
			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " IMS %nename secret IP secure sites no_external_associations ftp ";
			arne_build(SIM_Name, var_stat);

		}
		// check me some code is missing
		else if ((NE_Type.toLowerCase().contains("sgsn"))
				|| (NE_Type.toLowerCase().contains("pgm"))) {
			var_stat = ".createarne R6 "
					+ SIM_Name
					+ " SGSN %nename secret IP secure sites no_external_associations no_value ";
			arne_build(SIM_Name, var_stat);
		} else if ((NE_Type.toLowerCase().contains("h2s"))
				|| (NE_Type.toLowerCase().contains("mtas"))
				|| (NE_Type.toLowerCase().contains("epg"))
				|| (NE_Type.toLowerCase().contains("esapv"))
				|| (NE_Type.toLowerCase().contains("esasn"))
				|| (NE_Type.toLowerCase().contains("esapc"))
				|| (NE_Type.toLowerCase().contains("sasn"))
				|| (NE_Type.toLowerCase().contains("dsc"))
				|| (NE_Type.toLowerCase().contains("dua-s"))
				|| (NE_Type.toLowerCase().contains("wcg"))
				|| (NE_Type.toLowerCase().contains("bbsc"))) {
			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " IMS %nename secret IP secure sites no_external_associations ftp";
			arne_build(SIM_Name, var_stat);
		} else if (NE_Type.toLowerCase().contains("bsp")) {
			String var = NE_Names[0];
			Group_Name = var.substring(0, var.indexOf("0"));
			String var_group = null;
			for (int i = 0; i < No_of_Nodes; i++) {
				if (var_group == null) {
					var_group = NE_Names[i];
				} else {
					var_group = (var_group + "|" + NE_Names[i]);
				}
			}

			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " IMS %nename secret IP secure sites no_external_associations no_value "
					+ Group_Name + " " + var_group;
			arne_build(SIM_Name, var_stat);
		}

		else if (NE_Type.toLowerCase().contains("msc-s")) {
			String var = NE_Names[0];
			Group_Name = var.substring(0, var.indexOf("0"));
			var_stat = ".createarne R6 "
					+ SIM_Name
					+ " NETSim %nename secret IP secure sites no_external_associations no_value "
					+ Group_Name + " " + NE_Names[0];
			arne_build(SIM_Name, var_stat);
		} else if ((NE_Type.toLowerCase().contains("msc"))
				|| (NE_Type.toLowerCase().contains("bsc"))) {
			String var = NE_Names[0];
			Group_Name = var.substring(0, var.indexOf("0"));
			var_stat = ".createarne R12.2 "
					+ SIM_Name
					+ " NETSim %nename secret IP secure sites no_external_associations defaultgroups";
			arne_build(SIM_Name, var_stat);
		} else if ((NE_Type.toLowerCase().contains("cscf"))
				|| (NE_Type.toLowerCase().contains("upg"))
				|| (NE_Type.toLowerCase().contains("sdnc-p"))) {
			var_stat = ".createarne R6 "
					+ SIM_Name
					+ " NETSim %nename secret IP secure sites no_external_associations no_value";
			arne_build(SIM_Name, var_stat);
		} else {
			System.out
					.println("Error - Could not create ARNE XML, support not provided.");
			System.out
					.println("Please generate it manually & intimate to Automation team. So that support will be provided");
			// System.exit(0);
		}
	}
}
