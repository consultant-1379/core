package com.ericsson.simnet.core_automation;

import java.io.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class start_Create_Simulation {
	public void Automation(String SIM_Name, String NODE_Type, String Base_Name,
			String DEFAULT_Destination, int No_of_Nodes)
			throws InterruptedException, IOException {
		// Printing the given INPUTS
		System.out.println("");
		System.out.println("\t\t\t\t\t\tEntered Inputs");
		System.out.println("");
		System.out.println("\t\t\t\tSimulation Name          :      "
				+ SIM_Name);
		System.out.println("\t\t\t\tNODE Type                :      "
				+ NODE_Type);
		System.out.println("\t\t\t\tBase Name                :      "
				+ Base_Name);
		System.out.println("\t\t\t\tDEFAULT Destination      :      "
				+ DEFAULT_Destination);
		System.out.println("\t\t\t\tNo of Nodes              :      "
				+ No_of_Nodes);

		System.out.println("");

		System.out
				.println("%%%%%%%%%%%% printing informative messages regarding this process  %%%%%%%%%%%%%%");
		System.out.println("");

		InetAddress ip;
		String hostname, Port_Name, DD_Name;
		String[] port_names = new String[2];
		int l_max = 0;
		boolean Node_Type_Value = false, IP_value = false, attribute_value = false;
		ArrayList<String> free_ips = new ArrayList<String>();
		ArrayList<String> mml_names = new ArrayList<String>();

		ip = InetAddress.getLocalHost(); // getting ip
		hostname = ip.getHostName(); // getting hostname

		Run_IPs_Scripts ips = new Run_IPs_Scripts(); // Creating Fetch_FreeIps
														// object
		ips.check_directory(); // Checking whether dat directory exists or not
								// to store Total_Ips & Used_Ips
		ips.Total_Ips(); // Running Total_IPs Script
		ips.Used_Ips(); // Running Used_IPs Script

		// Getting free ips
		Get_Free_IPs freeips = new Get_Free_IPs(); // Creating Get_Free_IPs
													// object
		free_ips = freeips.get_ips(); // getting all the free ips from Total_IPs
										// & Used_IPs scripts

		String[] available_IPs = new String[free_ips.size()]; // declaring assIP
																// to store free
																// IPs
		available_IPs = free_ips.toArray(available_IPs); // Converting free_ips
															// Array List to
															// Array

		Get_Protocol map = new Get_Protocol();
		String protocol = map.getProtocol(NODE_Type); // Identifying the
														// Protocol for the
														// NEType given
		if (protocol == "NO_PROT") {
			System.out.println("Protocol support for this " + NODE_Type
					+ " is not available, pls contact Automation Team");
			System.exit(0);
		}
		// String NE_Name=NODE_Type.replaceAll("\\s", "-"); //Defining NE_Name
		String NE_Name = Base_Name;
		String[] NE_Names = new String[No_of_Nodes];

		for (int i = 0; i < (No_of_Nodes); i++) // Assigning NE_Names to all
												// Nodes
		{
			if (i < 9)
				NE_Names[i] = NE_Name + "0" + (i + 1);
			else
				NE_Names[i] = NE_Name + (i + 1);

		}

		check_Delete_runtime_files check_names = new check_Delete_runtime_files(); // checking
																					// whether
																					// any
																					// runtime
																					// generated
																					// files
																					// remained
																					// in
																					// the
																					// directory
		check_names.list_mml(NE_Names, l_max); // Deleting those runtime files
												// which are already existing

		create_Ports prt = new create_Ports(); // Creating Create_Ports object
		port_names = prt.select_ports(protocol, NE_Name, DEFAULT_Destination,
				hostname); // writing create ports,create DD commands to
							// "mml_ports.mml" file
		mml_names.add("mml_ports.mml");
		Port_Name = port_names[0]; // getting port name from Create_Ports class
		DD_Name = port_names[1]; // getting DD_Name from Create_Ports class

		// calling Create_NE class here
		create_NE nodeinf = new create_NE();
		nodeinf.create_ne(SIM_Name, NE_Name, NODE_Type, No_of_Nodes, Port_Name,
				DD_Name);
		mml_names.add("mml_cre_ne.mml"); // writing Create NE commands to
											// "mml_cre_ne.mml" file

		// calling script
		call_Script call = new call_Script(); // creating Call_Script object
		call.build_script(mml_names); // creating script with all mml files
										// generated upto now to run
		mml_names.removeAll(mml_names);

		exec_Script exec = new exec_Script();
		delete_Simulation del = new delete_Simulation(); // Creating
															// Delete_simulation
															// object

		String output = exec.executeCommand("./script.sh"); // executing the
															// script with mml
															// files

		DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String timeStamp = format.format(new Date());
		String Logs_File_Name = "Automation_Logs/" + timeStamp + "_logs.txt"; // creating
																				// logs
																				// file
																				// name
																				// with
																				// time
																				// stamp

		File newFile = new File(Logs_File_Name); // getting logs
		FileWriter fw = new FileWriter(newFile, true);
		fw.write(output);
		fw.close();

		// read_logs check_word=new read_logs(); //Creating Read_Logs object

		Read_from_file read_file = new Read_from_file();

		Node_Type_Value = read_file.check_word_in_file(Logs_File_Name,
				"does not exist"); // checking whether NETSim has eneterd Node
									// Type Support from logs

		if (Node_Type_Value == true) // deleting the intermediate files and
										// simulation created if node type
										// support is not there
		{
			del.del_sim(SIM_Name); // writing delete simulation commands to
									// "mml_del_sim.mml" file
			mml_names.add("mml_del_sim.mml");
			call.build_script(mml_names);
			mml_names.removeAll(mml_names);
			File newFile_1 = new File(Logs_File_Name);
			FileWriter fw_1 = new FileWriter(newFile_1, true);
			String output_1 = exec.executeCommand("./script.sh"); // executing
																	// the
																	// script to
																	// delete
																	// simulation
			fw_1.write(output_1);
			fw_1.close();
			check_names.list_mml(NE_Names, l_max);
			System.out
					.println("Error: we dont have support for entered Node Type "
							+ NODE_Type);
			System.out
					.println("please correct it in the configuration file and try again");
			System.exit(0);
		} else {
			System.out.println("INFO: NODE TYPE Validation Completed");
			System.out.println("INFO: Port & Default Destination were created");
			System.out.println("INFO: NE(s) created");
		}

		// calling assignports class here
		assign_Port asgn = new assign_Port(); // Creating Assign_Port object
		asgn.assign_IP(SIM_Name, NE_Names, Port_Name, No_of_Nodes,
				available_IPs); // assigning ports with free IPs
		mml_names.add("mml_assnports.mml");
		asgn.assign_DD(SIM_Name, No_of_Nodes, NE_Names, DD_Name); // assigning
																	// default
																	// destination
		mml_names.add("mml_assndd.mml");

		// calling stat_nes
		nodeinf.start_nes(SIM_Name, NE_Names[0]); // /writing start NE commands
													// to "mml_start_ne.mml"
													// file
		mml_names.add("mml_start_ne.mml");
		call.build_script(mml_names); // writing assign ports, assign DD, start
										// NE mml files to script
		mml_names.removeAll(mml_names);

		File newFile1 = new File(Logs_File_Name);
		FileWriter fw1 = new FileWriter(newFile1, true);

		String output1 = exec.executeCommand("./script.sh"); // executing the
																// script
		fw1.write(output1);
		fw1.close();

		IP_value = read_file.check_word_in_file(Logs_File_Name,
				"Error starting NE(s)"); // checking whether the node started
											// correctly or not
		if (IP_value == true) // deleting the intermediate files and simulation
								// created if NE(s) doesn't start
		{
			del.del_sim(SIM_Name);
			mml_names.add("mml_del_sim.mml");
			call.build_script(mml_names);
			mml_names.removeAll(mml_names);
			File newFile_1 = new File(Logs_File_Name);
			FileWriter fw_1 = new FileWriter(newFile_1, true);

			String output_1 = exec.executeCommand("./script.sh"); // executing
																	// the
																	// script to
																	// delete
																	// simulation
																	// created
			fw_1.write(output_1);
			fw_1.close();

			check_names.list_mml(NE_Names, l_max); // removing the intermediate
													// files(all mml files
													// generated upto now)
			System.out
					.println("we are getting error while trying to start the node may be problem with the IP or Port");
			System.out.println("please check the log file \"" + Logs_File_Name
					+ "\" for furthur information");
			System.exit(0);
		} else {
			System.out
					.println("INFO: assigning of ports & Default Destination completed");
			System.out.println("INFO: NE(s) Started with free IP(s) ");
		}

		if (NODE_Type.toLowerCase().contains("mgw")
				|| NODE_Type.toLowerCase().contains("epg")) // generating MOs
															// with
															// Automation logic
		{

			// getting xml file name
			// read_mim_name mim= new read_mim_name();
			String mim_file_name = read_file.get_mim_file_name(Logs_File_Name,
					NE_Names[0]);

			if (mim_file_name.equals("unknown")) {
				System.out
						.println("not able to get mim file name, please see the logs file \""
								+ Logs_File_Name + "\" for furthur information");
				System.exit(0);
			}

			String Directorty = mim_file_name.substring(0,
					mim_file_name.lastIndexOf('/'));
			// creating mp.dtd file (some xml files need mp.dtd file to get
			// parsed so creating empty file)

			check_File_in_Directory check_file = new check_File_in_Directory();
			System.out.println("Directory is " + Directorty);
			boolean mp_avail = check_file.check_file("mp.dtd", Directorty);

			if (!mp_avail) {
				// System.out.println("mp.dtd not avail");
				check_file.create_file(Directorty + "/mp.dtd");
			}

			ArrayList<String> Back_up_MOs = new ArrayList<String>();
			int No_of_MOs_per_Node = 4000;

			if (NODE_Type.toLowerCase().contains("m-mgw")) // MOs generation
															// block for M-MGw
			{

				No_of_MOs_per_Node = 3000;
				System.out.println("number of Mos to be scaled up "
						+ No_of_MOs_per_Node);
				create_MGw_MOs mo_gen = new create_MGw_MOs();
				System.out.println("Generating MOs...................");
				// read_present_no_of_MOs count=new read_present_no_of_MOs();
				// //reading the number of MOs already the NE had

				int Mos_to_be_Generated = No_of_MOs_per_Node
						- read_file.read_Original_MOs(NE_Names[0] + ".mo");

				// System.out.println("number of Mos to be genrated" +
				// Mos_to_be_Generated);

				mo_gen.MOs_generation(Mos_to_be_Generated); // generating the
															// kertyle file
															// which need to be
															// loaded on NE

				// calling to write MOFiles to script
				generate_mml_Kertyle ker_gen = new generate_mml_Kertyle();

				ker_gen.Kertyle_mml_script_MGw(SIM_Name, NE_Names); // writing
																	// kertyle
																	// commands
																	// to
																	// "mml_MOs.mml"
				mml_names.add("mml_MOs.mml");
				call.build_script(mml_names); // writing script which will scale
												// up MOs
				mml_names.removeAll(mml_names);

				File newFile4 = new File(Logs_File_Name);
				FileWriter fw4 = new FileWriter(newFile4, true);
				String output4 = exec.executeCommand("./script.sh"); // executing
																		// script
																		// which
				System.out.println("MGw Build is completed"); // will
				// scale
				// up
				// MOs
				fw4.write(output4);
				fw4.close();
			}
			if (NODE_Type.toLowerCase().contains("sgsn")
					|| NODE_Type.toLowerCase().contains("epg")) // MOs
																// generation
																// block for all
																// nodes except
																// M-MGw
			{
				// calling xml_read class here to generate MOs
				xml_read xml = new xml_read(); // creating object for xml_read
				// Back_up_MOs=xml.Get_MOs(SIM_Name,No_of_MOs_per_Node,
				// NE_Names[0], NODE_Type,MIM_Name, Logs_File_Name); //writing
				// kertyle files to scale up mos and getting the MOs list which
				// are generated
				Back_up_MOs = xml.Get_MOs(SIM_Name, 4000, NE_Names[0],
						NODE_Type, mim_file_name, Logs_File_Name); // writing
																	// kertyle
																	// files
																	// to
																	// scale
																	// up
																	// mos
																	// and
																	// getting
																	// the
																	// MOs
																	// list
																	// which
																	// are
																	// generated

				// calling to create .mo files to all nodes
				create_MOs cre_mo = new create_MOs(); // creating object for
														// Create_MOs
				cre_mo.generate_file(NE_Names, "Kertyle1.mo", "Kertyle"); // writing
																			// kertyle
																			// files
																			// for
																			// all
																			// nodes
																			// just
																			// by
																			// replacing
																			// node
																			// name
																			// from
																			// the
																			// initial
																			// kertyle
																			// generated

				generate_mml_Kertyle ker_gen = new generate_mml_Kertyle();
				ker_gen.Kertyle_mml_indi(SIM_Name, NE_Names);
				for (int i = 0; i < NE_Names.length; i++) {
					mml_names.add("mml_MOs_" + (i + 1) + ".mml");
				}
				call.build_script_parallel(mml_names);
				mml_names.removeAll(mml_names);

				File newFile4 = new File(Logs_File_Name);
				FileWriter fw4 = new FileWriter(newFile4, true);
				System.out
						.println("INFO: Generating MOs parallely...................");

				String[] output_mos = new String[NE_Names.length];

				output_mos = exec.exec_command_parallel(NE_Names.length);
				for (int c = 0; c < NE_Names.length; c++) {
					fw4.write(output_mos[c]);
				}

				fw4.close();
			}
			// checking whether MOs generated correctly in number
			if (NODE_Type.toLowerCase().contains("epg")) // checking the MOs
															// count generated
															// correctly or not
															// for all nodes
															// except M-MGw and
															// generating the
															// MOs defeciet
			{
				// read_present_no_of_MOs count_check=new
				// read_present_no_of_MOs(); //creating object for
				// Read_Present_No_of_MOs
				generate_Kertyle ker = new generate_Kertyle(); // creating
																// object for
																// Generate_Kertyle
				generate_mml_Kertyle gen_check = new generate_mml_Kertyle();

				int[] check_MOs = new int[NE_Names.length];
				int[] MO_Diff = new int[NE_Names.length];

				int l = 0, m = 0, identity = 321234;
				while (m < Back_up_MOs.size()) {
					int no_of_nodes_to_be_corrected = 0;

					for (int z = 0; z < NE_Names.length; z++) {
						check_MOs[z] = read_file.read_Original_MOs(NE_Names[z]
								+ "_final" + l + ".mo"); // getting the no of
															// MOs already the
															// node have
						MO_Diff[z] = No_of_MOs_per_Node - check_MOs[z];

						if (MO_Diff[z] > 0) {
							System.out
									.println("INFO: difference is there between MOs Count Required & MOs Count Generated for "
											+ NE_Names[z]
											+ ", So again generating the difference & difference is "
											+ MO_Diff[z]);
							ker.write_lines(
									Back_up_MOs.get(m).replaceAll(NE_Names[0],
											NE_Names[z]),
									Back_up_MOs.get(m + 1), MO_Diff[z],
									identity, "Kertyle_final" + (z + 1) + "_"
											+ l + ".mo", Logs_File_Name);

							// to write kertyle load to mml
							gen_check.Kertyle_mml_MOs_check(SIM_Name,
									NE_Names[z], "Kertyle_final" + (z + 1)
											+ "_" + l + ".mo",
									"mml_MOs_correct" + (z + 1) + "_" + l
											+ ".mml", (l + 1));
							mml_names.add("mml_MOs_correct" + (z + 1) + "_" + l
									+ ".mml");
							no_of_nodes_to_be_corrected++;
						}
					}
					if (no_of_nodes_to_be_corrected == 0)
						break;
					else {
						call.build_script_parallel(mml_names);
						mml_names.removeAll(mml_names);
						System.out
								.println("INFO: Generating MOs  parallely after checking count "
										+ (l + 1) + " time...................");

						File newFile4 = new File(Logs_File_Name);
						FileWriter fw4 = new FileWriter(newFile4, true);
						String[] output_mos = new String[no_of_nodes_to_be_corrected];
						output_mos = exec
								.exec_command_parallel(no_of_nodes_to_be_corrected);

						for (int c = 0; c < no_of_nodes_to_be_corrected; c++) {
							fw4.write(output_mos[c]);
						}
						fw4.close();
						l++;
						m = m + 2;
						identity = identity + 10000;
					}
				}

				l_max = l;
				if (m == Back_up_MOs.size()) {
					System.out
							.println("Sorry we cannot populate MOs for  correctly, for this NEType"
									+ NODE_Type);
					System.out
							.println("please correct it by adding some MOs manually");
				}
				System.out.println("starting setmos process");
				// calling setmos method to set mandatory attributes
				set_mandatory_MOs setattr = new set_mandatory_MOs();
				System.out
						.println("INFO: Checking whether we have any support for NE(s) to Sync");

				attribute_value = setattr.setMO(SIM_Name, NODE_Type, NE_Names,
						Logs_File_Name);
				if (attribute_value) {
					mml_names.add("mml_set_mos.mml");
					mml_names.add("mml_create_mos.mml");
					call.build_script(mml_names);
					mml_names.removeAll(mml_names);
					// connecting to shell and getting logs
					File newFile3 = new File(Logs_File_Name);
					FileWriter fw3 = new FileWriter(newFile3, true);
					String output3 = exec.executeCommand("./script.sh");
					fw3.write(output3);
					fw3.close();
				} else
					System.out
							.println("INFO: we are not setting any attributes for these NE(s), if required contact automation team ");

			}
		} else if (NODE_Type.toLowerCase().contains("sgsn")) // generating MOs
		// according to
		// structure
		{

			// getting xml file name
			// read_mim_name mim= new read_mim_name();
			String mim_file_name = read_file.get_mim_file_name(Logs_File_Name,
					NE_Names[0]);

			if (mim_file_name.equals("unknown")) {
				System.out
						.println("not able to get mim file name, please see the logs file \""
								+ Logs_File_Name + "\" for furthur information");
				System.exit(0);
			}

			String Directorty = mim_file_name.substring(0,
					mim_file_name.lastIndexOf('/')); // reading mimfile
														// directory
			// creating mp.dtd file (some xml files need mp.dtd file to get
			// parsed so creating empty file)
			check_File_in_Directory check_file = new check_File_in_Directory();
			boolean mp_avail = check_file.check_file("mp.dtd", Directorty);

			if (!mp_avail) {
				check_file.create_file(Directorty + "/mp.dtd"); // creating
																// mp.dtd
			}

			// Deleting NE Database and generating Kertyle for 1 node

			delete_database del_db = new delete_database();
			del_db.delete_db(SIM_Name, NE_Names[0]);

			mml_names.add("mml_del_db_ne.mml"); // createing mml
			call.build_script(mml_names); // writing delete db to script
			mml_names.removeAll(mml_names);

			File newFile9 = new File(Logs_File_Name);
			FileWriter fw9 = new FileWriter(newFile9, true);

			String output9 = exec.executeCommand("./script.sh"); // executing
																	// the
																	// script
																	// containg
																	// mmls of
																	// sddign.createport,ipaddress
			fw9.write(output9);// writing output of script.sh to LOGS
			fw9.close();

			read_generate_predefined_MOs gen_MOs_def = new read_generate_predefined_MOs();
			gen_MOs_def.Read_MOs(SIM_Name, 161, mim_file_name, NE_Names[0]
					+ ".mo", Logs_File_Name, NE_Names);

			System.out.println("came back to main function");
			System.out.println("starting setmos process");
			// calling setmos method to set mandatory attributes
			set_mandatory_MOs setattr = new set_mandatory_MOs();
			System.out
					.println("INFO: Checking whether we have any support for NE(s) to Sync");
			System.out.println("INFO: Sgsn Build completed");

			attribute_value = setattr.setMO(SIM_Name, NODE_Type, NE_Names,
					Logs_File_Name);
			if (attribute_value) {
				mml_names.add("mml_set_mos.mml");
				mml_names.add("mml_create_mos.mml");
				call.build_script(mml_names);
				mml_names.removeAll(mml_names);
				// connecting to shell and getting logs
				File newFile3 = new File(Logs_File_Name);
				FileWriter fw3 = new FileWriter(newFile3, true);
				String output3 = exec.executeCommand("./script.sh");
				fw3.write(output3);
				fw3.close();
			} else
				System.out
						.println("INFO: we are not setting any attributes for these NE(s), if required contact automation team ");
		}

		// calling Generate_Arne class here

		// calling save_simulation
		nodeinf.save_simulation(SIM_Name);
		mml_names.add("mml_save_sim.mml");
		// calling script & getting logs
		call.build_script(mml_names);
		mml_names.removeAll(mml_names);
		File newFile6 = new File(Logs_File_Name);
		FileWriter fw6 = new FileWriter(newFile6, true);
		String output6 = exec.executeCommand("./script.sh");
		fw6.write(output6);
		fw6.close();

		// copying Simulation to FTP Client

		// copying Simulation to NEXUS

	}
}
