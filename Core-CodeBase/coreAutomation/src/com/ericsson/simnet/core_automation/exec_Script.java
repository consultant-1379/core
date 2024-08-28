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
import java.io.*;

public class exec_Script {

	public String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		// String command="./script.sh";
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public String[] exec_command_parallel(int No_of_scripts_to_be_generated) {
		StringBuffer[] output = new StringBuffer[No_of_scripts_to_be_generated];
		String[] command = new String[No_of_scripts_to_be_generated];
		BufferedReader[] br = new BufferedReader[No_of_scripts_to_be_generated];
		Process[] prc = new Process[No_of_scripts_to_be_generated];
		String[] out = new String[No_of_scripts_to_be_generated];
		try {
			for (int i = 0; i < No_of_scripts_to_be_generated; i++) {
				command[i] = "./script" + (i + 1) + ".sh";
				File file = new File("script" + (i + 1) + ".sh");
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
				prc[i] = Runtime.getRuntime().exec(command[i]);
				br[i] = new BufferedReader(new InputStreamReader(
						prc[i].getInputStream()));
			}
			for (int j = 0; j < No_of_scripts_to_be_generated; j++) {
				prc[j].waitFor();

				String line;
				output[j] = new StringBuffer("");

				while ((line = br[j].readLine()) != null)
					output[j].append(line + "\n");

				out[j] = output[j].toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("returning back to parent fun after executing parallely");
		return out;
	}
}
