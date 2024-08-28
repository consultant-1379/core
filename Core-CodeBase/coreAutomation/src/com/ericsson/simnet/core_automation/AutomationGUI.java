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

import java.io.IOException;
import java.net.UnknownHostException;

public class AutomationGUI {

	String SIM_Name, NODE_Type, Base_Name, MIM_Name;
	static String DEFAULT_Destination = "192.168.100.10";
	static boolean Simulation_Available;
	boolean mim_file_available;

	public static void main(String[] args) throws IOException {

		if (args.length != 4) {
			System.out
					.println("Proper Usage is: java -jar Automation.jar <simulationName> <neType> <baseName> <noOfNodes>");
			System.exit(0);
		}

		String SIM_Name = args[0];
		String NODE_Type = args[1];
		String Base_Name = args[2];
		int NO_of_Nodes = Integer.parseInt(args[3]);

		System.out.println("Checking if simulation alredy exist or not");
		check_File_in_Directory check_sim_name = new check_File_in_Directory();
		Simulation_Available = check_sim_name.check_file(SIM_Name,
				"/netsim/netsimdir");

		System.out.println("INFO: Starting simulation Build");

		start_Create_Simulation start_process = new start_Create_Simulation();
		try {

			start_process.Automation(SIM_Name, NODE_Type, Base_Name,
					DEFAULT_Destination, NO_of_Nodes);

			System.exit(0);
		}

		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
