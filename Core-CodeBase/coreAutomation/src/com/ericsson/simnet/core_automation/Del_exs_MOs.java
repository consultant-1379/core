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
import java.util.ArrayList;

public class Del_exs_MOs {

	public void read_all_MOs(String SIM_Name, String[] NE_Names,
			String initial_kertye, String final_Kertyle,
			ArrayList<String> MO_Type_generated,
			ArrayList<Integer> MO_Count_generated, String Logs_File_Name)
			throws IOException {
		ArrayList<String> all_lines = new ArrayList<String>();
		ArrayList<String> Total_MOs = new ArrayList<String>();
		ArrayList<String> MOs_init = new ArrayList<String>();
		ArrayList<Integer> Count_MOs = new ArrayList<Integer>();

		ArrayList<String> parent_line = new ArrayList<String>();
		ArrayList<String> identity = new ArrayList<String>();
		ArrayList<String> MO_Type = new ArrayList<String>();

		ArrayList<String> moid = new ArrayList<String>();

		Read_from_file read = new Read_from_file();
		all_lines = read.write_file_to_arrlist(final_Kertyle);

		MOs_init = read.read_MOs_from_file(initial_kertye);

		for (int i = 0; i < MOs_init.size(); i++) {
			Total_MOs.add(MOs_init.get(i));
			Count_MOs.add(1);
		}
		for (int j = 0; j < MO_Type_generated.size(); j++) {
			Total_MOs.add(MO_Type_generated.get(j));
			Count_MOs.add(MO_Count_generated.get(j));
		}

		System.out.println("the size of MOs_init is " + MOs_init.size());
		System.out.println("the size of MO_Type_generated is "
				+ MO_Type_generated.size());
		System.out.println("the size of final list is " + Total_MOs.size());
		for (int i = 0; i < Total_MOs.size(); i++) {
			String S = "    moType " + Total_MOs.get(i);
			// System.out.println(S);
			for (int j = 0; j < Count_MOs.get(i); j++) {
				int k;
				for (k = 0; k < all_lines.size(); k++) {
					if (all_lines.get(k).equals(S)) {
						all_lines.remove(k);
						break;
					}
					/*
					 * else { System.out.println("entered else loop");
					 * System.out
					 * .println("cannot able to find out this MO "+Total_MOs
					 * .get(i)+" for "+j+"th time"); }
					 */
				}
				if (k == all_lines.size()) {
					System.out.println("cannot able to find out this MO "
							+ Total_MOs.get(i) + " for " + j + "th time");
				}
			}
		}
		for (int n = 0; n < all_lines.size(); n++) {
			if (all_lines.get(n).contains("moType ")) {
				parent_line.add(all_lines.get(n - 2));
				identity.add(all_lines.get(n - 1));
				MO_Type.add(all_lines.get(n));
				// all_lines.get(k).replaceAll(all_lines.get(k), "");

			}
		}
		System.out.println("entered delete mos upto ldn ");
		Prepare_ldn get_ldn = new Prepare_ldn();
		moid = get_ldn.get_moid_Kertyle(parent_line, identity, MO_Type);
		generate_mml_Kertyle mml_del = new generate_mml_Kertyle();

		generate_Kertyle gen_ker_del = new generate_Kertyle();
		gen_ker_del.delete_mos(moid, "kertyle_del_1.mo");

		create_MOs del_mo = new create_MOs();
		del_mo.generate_file(NE_Names, "kertyle_del_1.mo", "kertyle_del_");

		// mml_del.Kertyle_mml_MOs_del(SIM_Name, NE_Names,moid);
		mml_del.Kertyle_mml_MOs_del(SIM_Name, NE_Names);
		create_run_del_script(SIM_Name, NE_Names.length, Logs_File_Name);

		System.out.println("Deleting MOs process completed ");
	}

	public void create_run_del_script(String SIM_Name, int no_of_NEs,
			String Logs_File_Name) throws IOException {
		ArrayList<String> mml_names = new ArrayList<String>();

		// calling to write MOFiles to script
		// generate_mml_kertyle_indi ker_gen=new generate_mml_kertyle_indi();
		call_Script call = new call_Script(); // creating Call_Script object
		exec_Script exec = new exec_Script();

		// ker_gen.Kertyle_mml(SIM_Name, NE_Names);
		for (int i = 0; i < no_of_NEs; i++) {
			mml_names.add("mml_MOs_del_" + (i + 1) + ".mml");
		}
		call.build_script_parallel(mml_names);
		mml_names.removeAll(mml_names);

		File newFile = new File(Logs_File_Name);
		FileWriter fw = new FileWriter(newFile, true);
		System.out
				.println("INFO: Deleting excess MOs parallely...................");

		String[] output_mos = new String[no_of_NEs];
		// System.out.println("BEFORE EXECUTING DELETE COMMAND");
		output_mos = exec.exec_command_parallel(no_of_NEs);
		// System.out.println("AFTER EXECUTING DELETE COMMAND");
		for (int c = 0; c < no_of_NEs; c++) {
			// System.out.println("enered printting logs loop");
			fw.write(output_mos[c]);
		}

		fw.close();

		// System.out.println("DELETING PROCESS COMPLETED");
		// System.out.println("COMING OUT OF SCRIPT LOOP");
	}
}
