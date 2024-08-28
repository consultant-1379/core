package com.ericsson.simnet.core_automation;

import java.io.*;
import java.util.ArrayList;

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

public class get_MOs_count {

	public ArrayList<String> divide(String SIM_Name,
			ArrayList<String> MO_Child, ArrayList<String> MO_Parent_String,
			ArrayList<Integer> No_of_available_MOs, int No_of_MOs,
			String Kertyle_File_Name, String Logs_File_Name) throws IOException {
		int Contribution_MOs, Counter, tmp_MOs_Count, Identity = 123;
		ArrayList<String> MO_Child_Selected = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Selected = new ArrayList<Integer>();
		ArrayList<String> MO_Parent_String_Selected = new ArrayList<String>();
		ArrayList<String> MO_Parent_Line_Selected = new ArrayList<String>();
		ArrayList<String> MO_Parent_Line_Selected_Final = new ArrayList<String>();
		ArrayList<String> MO_Parent_String_Selected_Final = new ArrayList<String>();
		ArrayList<String> MO_Child_Selected_Final = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Selected_Final = new ArrayList<Integer>();
		ArrayList<Integer> MOs_count = new ArrayList<Integer>();
		ArrayList<String> Back_up_MOs = new ArrayList<String>();
		ArrayList<String> mml_names = new ArrayList<String>();
		Kertyle_read read = new Kertyle_read();
		// System.out.println("the parent size is "+MO_Parent_String.size()+" the child sixe is "+MO_Child.size());
		// read_present_no_of_MOs count=new read_present_no_of_MOs();
		Read_from_file read_file = new Read_from_file();
		No_of_MOs = (No_of_MOs - read_file.read_Original_MOs(Kertyle_File_Name));
		tmp_MOs_Count = No_of_MOs;
		int expected_count = largestPowerOf2(No_of_MOs);
		// System.out.println("NO_OF_MOS"+No_of_MOs);

		while (expected_count > 1) {
			Counter = 0;
			Contribution_MOs = (No_of_MOs / expected_count) + 1;
			// System.out.println("expected_count"+expected_count);
			// System.out.println("Contribution"+Contribution_MOs);
			for (int j = 0; j < No_of_available_MOs.size(); j++) {
				if (No_of_available_MOs.get(j) > Contribution_MOs
						&& (!MO_Child.get(j).contains("MeasurementReader"))
						&& (!MO_Child.get(j).contains("Pm"))
						&& (!MO_Child.get(j).contains("PM"))) {
					MO_Child_Selected.add(MO_Child.get(j));
					MO_Parent_String_Selected.add(MO_Parent_String.get(j));
					MO_Count_Selected.add(No_of_available_MOs.get(j));
					Counter = Counter + 1;
				}
			}

			if (Counter >= expected_count) {
				// call Kertyle_read file
				MO_Parent_Line_Selected = read.Get_Parent(Kertyle_File_Name,
						MO_Child_Selected);
				generate_Kertyle ker = new generate_Kertyle();

				for (int n = 0; n < MO_Parent_Line_Selected.size(); n++) {
					if (MO_Parent_Line_Selected.get(n) != null) {
						MO_Parent_Line_Selected_Final
								.add(MO_Parent_Line_Selected.get(n));
						MO_Child_Selected_Final.add(MO_Child_Selected.get(n));
						MO_Count_Selected_Final.add(MO_Count_Selected.get(n));
						MO_Parent_String_Selected_Final
								.add(MO_Parent_String_Selected.get(n));
					}
				}

				if (MO_Parent_Line_Selected_Final.size() >= expected_count) {
					int a = 0;
					get_child_count_of_MOs test = new get_child_count_of_MOs();
					MOs_count = test.get_count_MOs(MO_Child_Selected_Final,
							MO_Parent_String_Selected_Final);

					while (tmp_MOs_Count > 0) {
						while (a < MO_Parent_Line_Selected_Final.size()) {
							if ((MOs_count.get(a) == 1)
									&& (MO_Count_Selected_Final.get(a) > 10000)) {
								// System.out.println("entered if loop for mos check");
								Back_up_MOs.add(MO_Parent_Line_Selected_Final
										.get(a));
								Back_up_MOs.add(MO_Child_Selected_Final.get(a));
							}

							if (tmp_MOs_Count > Contribution_MOs) {

								// to write to kertyle file for generation of
								// MOs
								int count_MOs = (Contribution_MOs / MOs_count
										.get(a));
								Identity = ker.write_lines(
										MO_Parent_Line_Selected_Final.get(a),
										MO_Child_Selected_Final.get(a),
										count_MOs, Identity, "Kertyle1.mo",
										Logs_File_Name);
								tmp_MOs_Count = (tmp_MOs_Count - (count_MOs * MOs_count
										.get(a)));

								/*
								 * System.out.println(
								 * "MO Parent Selected final size of list"
								 * +MO_Parent_Line_Selected_Final.size());
								 * System
								 * .out.println("present iteration number "
								 * +(a+1)); System.out.println(
								 * "Remaining MOs cout to be generated "
								 * +tmp_MOs_Count);
								 * System.out.println("MO Parent Name Selected "
								 * +MO_Parent_Line_Selected_Final.get(a));
								 * System.out.println("MO Child Name Selected "+
								 * MO_Child_Selected_Final.get(a));
								 * System.out.println
								 * ("no of mos we will get by creatin this "
								 * +MOs_count.get(a));
								 * System.out.println("this MOS we are creating "
								 * +count_MOs);
								 */
								a++;
							} else if (tmp_MOs_Count <= 0)
								break;
							else if (tmp_MOs_Count <= Contribution_MOs) {
								int count_MOs = (tmp_MOs_Count / MOs_count
										.get(a));
								Identity = ker.write_lines(
										MO_Parent_Line_Selected_Final.get(a),
										MO_Child_Selected_Final.get(a),
										count_MOs, Identity, "Kertyle1.mo",
										Logs_File_Name);
								tmp_MOs_Count = (tmp_MOs_Count - (count_MOs * MOs_count
										.get(a)));
								// System.out.println("i entered else of printing");
								a++;
								// break;
								if (tmp_MOs_Count <= 0) {
									break;
								} else
									continue;

							}

						}
					}
					break;
				} else {
					expected_count = (expected_count / 2);
					MO_Child_Selected_Final.removeAll(MO_Child_Selected_Final);
					MO_Parent_Line_Selected_Final
							.removeAll(MO_Parent_Line_Selected_Final);
					MO_Count_Selected_Final.removeAll(MO_Count_Selected_Final);
					MO_Parent_String_Selected_Final
							.removeAll(MO_Parent_String_Selected_Final);
					MO_Child_Selected.removeAll(MO_Child_Selected);
					MO_Parent_Line_Selected.removeAll(MO_Parent_Line_Selected);
					MO_Count_Selected.removeAll(MO_Count_Selected);
					MO_Parent_String_Selected
							.removeAll(MO_Parent_String_Selected);
					Back_up_MOs.removeAll(Back_up_MOs);

				}
			} else {
				expected_count = (expected_count / 2);
				MO_Child_Selected.removeAll(MO_Child_Selected);
				MO_Parent_Line_Selected.removeAll(MO_Parent_Line_Selected);
				MO_Count_Selected.removeAll(MO_Count_Selected);
				MO_Parent_String_Selected.removeAll(MO_Parent_String_Selected);
				Back_up_MOs.removeAll(Back_up_MOs);
			}

		}
		if (expected_count == 1) {
			exec_Script exec = new exec_Script();
			delete_Simulation del = new delete_Simulation();
			check_Delete_runtime_files check_names = new check_Delete_runtime_files();
			call_Script call = new call_Script();
			del.del_sim(SIM_Name);
			mml_names.add("mml_del_sim.mml");
			call.build_script(mml_names);
			mml_names.removeAll(mml_names);

			File newFile_1 = new File(Logs_File_Name);
			FileWriter fw_1 = new FileWriter(newFile_1, true);

			String output_1 = exec.executeCommand("./script.sh");
			fw_1.write(output_1);
			fw_1.close();

			// deleting all the intermediate files generated

			String[] str = new String[0];
			check_names.list_mml(str, 0);

			System.out.println("we cannot populate the Required no of MOs");
			System.out
					.println("May be the entered mim file name doesnot belong to the netype");
			System.exit(0);

		} else
			System.out.println("INFO: MOs selection Process Completed");

		return Back_up_MOs;
	}

	public int largestPowerOf2(int n) {
		int res = 2;
		while (res < n) {
			res = res * 2;
		}
		return res;
	}
}
