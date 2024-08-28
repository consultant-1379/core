package com.ericsson.simnet.core_automation;

import java.io.BufferedReader;
import java.io.FileReader;
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

public class Kertyle_read {

	public ArrayList<String> Get_Parent(String File_Name,
			ArrayList<String> MO_Child) {
		// String MO_Parent=null;
		ArrayList<String> MO_Parent = new ArrayList<String>();
		// MO_Child ="moType SgsnMmeBRM:BrmBackupManager";
		try {

			// Create object of FileReader
			FileReader mo_file = new FileReader(File_Name);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(mo_file);

			// Variable to hold the one line data
			String line1 = null;
			ArrayList<String> a1 = new ArrayList<String>();

			int sa1 = 0;
			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);
			}
			BR1.close();
			sa1 = a1.size();

			int i;
			// System.out.println(MO_Child);
			for (i = 0; i < MO_Child.size(); i++) {
				int j = 0;
				for (j = 2; j < sa1; j++) {
					// System.out.println(j+"    moType "+MO_Child.get(i));

					if ((a1.get(j)).equals("    moType " + MO_Child.get(i))) {
						// System.out.println(a1.get((j-2)));
						MO_Parent.add(a1.get((j - 2)));
						a1.remove(j);
						a1.add(j, "SRIKANTH");
						break;
					} else {
						// System.out.println("child"+MO_Child.get(i));
						// System.out.println("child"+MO_Parent.get(j-2));
						// System.out.println("not found"+j);
					}
				}
				if (j == sa1) {
					// System.out.println("MO Type "+ MO_Child.get(i));
					System.out
							.println("cannot able to find parent line of this MOType "
									+ MO_Child.get(i));
					MO_Parent.add(null);
				}
			}
		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Kertyle_Read"
							+ e.getMessage());
		}
		return MO_Parent;

	}

	public int Get_indi_MO_Count(String File_Name, String MO_Type) {
		int Count = 0;
		try {

			// Create object of FileReader
			FileReader mo_file = new FileReader(File_Name);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(mo_file);

			// Variable to hold the one line data
			String line1 = null;
			ArrayList<String> a1 = new ArrayList<String>();

			int sa1 = 0;
			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);
			}
			BR1.close();
			sa1 = a1.size();

			int j = 0;
			for (j = 2; j < sa1; j++) {
				if ((a1.get(j)).equals("    moType " + MO_Type)) {
					Count++;
					// break;
				}

			}
			if ((j == sa1) && (Count == 0)) {
				// System.out.println("This "+MO_Type+" is not ther upto now. generating full count requested for this");
				// System.out.println("count = "+Count);
			}

		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Kertyle_Read_fun_2"
							+ e.getMessage());
		}
		return Count;
	}

	public ArrayList<String> Get_all_MO_Names(String File_Name, String MO_Type) {

		ArrayList<String> Identity_Line = new ArrayList<String>();
		ArrayList<String> Identity = new ArrayList<String>();
		//
		int Count = 0;
		try {

			// Create object of FileReader
			FileReader mo_file = new FileReader(File_Name);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(mo_file);

			// Variable to hold the one line data
			String line1 = null;
			ArrayList<String> a1 = new ArrayList<String>();

			int sa1 = 0;
			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);
			}
			BR1.close();
			sa1 = a1.size();

			int j = 0;
			for (j = 2; j < sa1; j++) {
				if ((a1.get(j)).equals("    moType " + MO_Type)) {
					Identity_Line.add(a1.get(j - 1));
					Count++;
				}

			}
			if ((j == sa1) && (Count == 0)) {
				System.out
						.println("This "
								+ MO_Type
								+ " is not found in the recently generated kertyle file");
				// System.out.println("count = "+Count);
			}

			// getting Identity
			for (int k = 0; k < Identity_Line.size(); k++) {
				String l = Identity_Line.get(k).replace("\"", "");
				String name = l.replace("identity ", "");
				name = name.trim();
				// System.out.println("name = "+name);
				Identity.add(name);
			}

		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Kertyle_Read_fun_2"
							+ e.getMessage());
		}

		//

		return Identity;
	}

}
