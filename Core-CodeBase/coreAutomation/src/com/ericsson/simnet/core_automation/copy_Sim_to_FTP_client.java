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

public class copy_Sim_to_FTP_client {

	public void ftp_client(String SIM_Name_Zip, String FTP_IP_Address,
			String FTP_Destination_Path) {

		try {
			File file = new File("script.sh");
			FileWriter fw = new FileWriter("script.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("#!/bin/bash");
			pw.println("SPATH=\"/netsim/netsimdir/\";");
			pw.println("DPATH=\"" + FTP_Destination_Path + "\";");
			pw.println("HOST='" + FTP_IP_Address + "'");
			// pw.println("HOST='159.107.220.96'");
			pw.println("USER='simadmin'");
			pw.println("PASSWD='simadmin'");
			pw.println("FILE=" + SIM_Name_Zip);
			pw.println("cd $SPATH");
			pw.println("ftp -n $HOST <<END_SCRIPT");
			pw.println("quote USER $USER");
			pw.println("quote PASS $PASSWD");
			pw.println("cd $DPATH");
			pw.println("put $FILE");
			// pw.println("echo printing check");
			pw.println("quit");
			pw.println("END_SCRIPT");
			pw.println("exit 0");
			pw.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void ftp_client_Validate(String SIM_Name_Zip, String FTP_IP_Address,
			String FTP_Destination_Path) {

		try {
			File file = new File("script.sh");
			FileWriter fw = new FileWriter("script.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("#!/bin/bash");
			pw.println("SPATH=\"/netsim/netsimdir/\";");
			pw.println("DPATH=\"" + FTP_Destination_Path + "\";");
			pw.println("HOST='" + FTP_IP_Address + "'");
			// pw.println("HOST='159.107.220.96'");
			pw.println("USER='simadmin'");
			pw.println("PASSWD='simadmin'");
			pw.println("FILE=" + SIM_Name_Zip);
			pw.println("cd $SPATH");
			pw.println("ftp -n $HOST <<END_SCRIPT");
			pw.println("quote USER $USER");
			pw.println("quote PASS $PASSWD");
			pw.println("cd $DPATH");
			// pw.println("echo printing check");
			pw.println("quit");
			pw.println("END_SCRIPT");
			pw.println("exit 0");
			pw.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
