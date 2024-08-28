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

public class check_File_in_Directory {

	public boolean check_file(String File_Name, String Directory) {
		String s = null;
		boolean status = true;

		try {
			Process p = Runtime.getRuntime().exec(
					new String[] { "/bin/sh", "-c", "ls " + Directory });

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			while ((s = stdInput.readLine()) != null) {
				if (s.equals(File_Name)) {
					status = true;
					break;

				} else
					status = false;

			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			System.out.println("exception..");
			e.printStackTrace();
			System.exit(-1);
		}
		return status;
	}

	public void create_file(String File_Name) {
		try {
			File file = new File(File_Name);
			FileWriter fw = new FileWriter(File_Name);
			file.setReadable(true);
			file.setWritable(true);

			PrintWriter pw = new PrintWriter(fw);
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
