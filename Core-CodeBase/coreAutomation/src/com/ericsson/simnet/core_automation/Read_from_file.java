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

public class Read_from_file {

	public int read_Original_MOs(String generated_Kertyle_File_Name) {
		String last_line = null;
		try {

			// Create object of FileReader
			FileReader dumpmotree = new FileReader(generated_Kertyle_File_Name);

			// Instantiate the BufferedReader Class
			BufferedReader BR = new BufferedReader(dumpmotree);

			// Variable to hold the one line data
			String line1 = null;

			while ((line1 = BR.readLine()) != null) {
				last_line = line1;
			}
			BR.close();

		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Read_Present_No_of_MOs"
							+ e.getMessage());
		}

		String l = last_line.replace("\"", "");
		String m = l.replace("ECHO Number of MOs: ", "");
		int n = Integer.parseInt(m);
		// System.out.println("total count of MOs upto now is "+n);
		return n;
	}

	public String get_mim_file_name(String Logs_File_Name, String NE_Name) {
		String mim_file_name = null, mim_file_line = null;
		ArrayList<String> a1 = new ArrayList<String>();
		// MO_Child ="moType SgsnMmeBRM:BrmBackupManager";
		try {

			// Create object of FileReader
			FileReader logs_file = new FileReader(Logs_File_Name);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(logs_file);

			// Variable to hold the one line data
			String line1 = null;

			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);// a1 contains lofilefile
			}
			BR1.close();
			// System.out.println("size ="+a1.size());
			int i;
			for (i = 0; i < (a1.size() - 1); i++) {
				// System.out.println(i);
				if (a1.get(i).contains(
						"e: csmim:get_mim_file_name_by_nename(\"" + NE_Name
								+ "\").")) {
					break;
				}
			}

			// {ok, MimFile}
			mim_file_line = a1.get(i + 1);
			if (mim_file_line.contains("ok")) {
				mim_file_name = mim_file_line.replace("{ok,", "")
						.replace("}", "").replace("\"", ""); // extracting
																// mimfilename
				// System.out.println("mim_file_line= "+mim_file_line);
				// System.out.println("mim_file_name= "+mim_file_name);
			} else
				mim_file_name = "unknown";
		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:read_mim_name"
							+ e.getMessage());
		}

		return mim_file_name;
	}

	public boolean check_word_in_file(String File_Name, String statement) {
		boolean status = false;
		String line1 = null;
		try {
			FileReader fr = new FileReader(File_Name);
			BufferedReader BR = new BufferedReader(fr);

			while ((line1 = BR.readLine()) != null) {
				if (line1.contains(statement)) {
					status = true;
					break;
				} else
					status = false;
			}
			BR.close();
		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Read_Logs"
							+ e.getMessage());
		}
		return status;
	}

	public ArrayList<String> write_file_to_arrlist(String File_to_be_read)
			throws IOException {
		ArrayList<String> all_lines = new ArrayList<String>();

		// Create object of FileReader
		FileReader file = new FileReader(File_to_be_read);

		// Instantiate the BufferedReader Class
		BufferedReader BR1 = new BufferedReader(file);

		// Variable to hold the one line data
		String line1;

		while ((line1 = BR1.readLine()) != null) {
			all_lines.add(line1);
		}

		BR1.close();

		return all_lines;
	}

	public ArrayList<String> read_MOs_from_file(String File_to_be_read) {
		ArrayList<String> Type_Line = new ArrayList<String>();
		ArrayList<String> Type = new ArrayList<String>();
		// String last_line=null;
		try {

			// Create object of FileReader
			FileReader file = new FileReader(File_to_be_read);

			// Instantiate the BufferedReader Class
			BufferedReader BR = new BufferedReader(file);

			// Variable to hold the one line data
			String line1 = null;

			while ((line1 = BR.readLine()) != null) {
				if (line1.contains("moType")) {

					Type_Line.add(line1);
				}
			}
			BR.close();

			for (int i = 0; i < Type_Line.size(); i++) {
				String line2 = Type_Line.get(i).trim();
				Type.add(line2.replaceAll("moType ", ""));
			}
			/*
			 * for(int i=0;i<Type.size();i++) {
			 * 
			 * System.out.println("MOs names "+Type.get(i)); }
			 */
		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:read_MOs_from_file function "
							+ e.getMessage());
		}
		return Type;
	}

}
