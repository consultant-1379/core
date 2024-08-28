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

public class create_NE {

	public void create_ne(String simName, String neName, String neType,
			int numOfNes, String nePort, String neDest) {
		try {

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_cre_ne.mml", true)));
			out.println(".new simulation " + simName);
			out.println(".open " + simName);
			out.println(".new simne -auto " + numOfNes + " " + neName + " 1");
			out.println(".set netype " + neType);
			out.println(".set port " + nePort);
			out.println(".set subaddr 0");
			out.println(".set external " + neDest);
			out.println(".set save");
			out.println(".set save");
			out.println(".show simnes");
			out.println(".show selected");
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save_simulation(String SIM_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_save_sim.mml", true)));
			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".start -parallel");
			out.println(".saveandcompress " + SIM_Name + " force");
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start_nes(String SIM_Name, String NE_Name) {
		try {

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_start_ne.mml", true)));

			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".start -parallel");
			// out.println(".sleep 5");
			if (SIM_Name.toLowerCase().contains("mgw")
					|| SIM_Name.toLowerCase().contains("epg")
					|| SIM_Name.toLowerCase().contains("sgsn")) {
				out.println(".mogenerator generate");

				// out.println(".sleep 5");
				out.println(".stop ");
				out.println(".select " + NE_Name);
				out.println(".start");
				// out.println(".sleep 5");
				out.println("e: csmim:get_mim_file_name_by_nename(\"" + NE_Name
						+ "\").");
				out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
						+ get_addr(NE_Name + ".mo") + "\";");
				// out.println(".sleep 5");
				out.println(".stop");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get_addr(String name) {
		String Path;
		File directory = new File(name);
		boolean isDirectory = directory.isDirectory();
		if (isDirectory) {
			Path = directory.getAbsolutePath();
		} else {
			Path = directory.getAbsolutePath();
		}

		return Path;

	}
}
