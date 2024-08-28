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

public class copy_Sim_to_NEXUS {

	public void connect_to_Nexus(String SIM_Name, String UserName,
			String Password, String Version, String Classifier) {
		String SIM_Name_Zip = SIM_Name + ".zip";
		String NEXUS_Destination_Path = "https://arm901-eiffel004.athtem.eei.ericsson.se:8443/nexus/content/repositories/simnet/com/ericsson/simnet/";
		String FILE = SIM_Name + "_" + Version + "_" + Classifier + ".zip";
		try {
			File file = new File("script.sh");
			FileWriter fw = new FileWriter("script.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("#!/bin/bash");
			pw.println("SPATH=\"/netsim/netsimdir/\";");
			pw.println("cd $SPATH");
			pw.println("cp " + SIM_Name_Zip + " " + FILE);
			pw.println("curl -k --upload-file " + FILE + " -u " + UserName
					+ ":" + Password + " -v " + NEXUS_Destination_Path + FILE);
			pw.println("if [ $? -eq 0 ]");
			pw.println("then");
			pw.println("echo \"Successfully created file\"");
			pw.println("exit 0");
			pw.println("else");
			pw.println("echo \"Could not create file \"");
			pw.println("exit 1");
			pw.println("fi");
			pw.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
