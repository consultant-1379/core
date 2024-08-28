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

import java.util.ArrayList;

public class Prepare_ldn {

	public ArrayList<String> get_moid(ArrayList<String> Parent_line,
			ArrayList<String> index_line, ArrayList<String> MO_type) {
		// String
		// Parent_Line="parent \"SgsnMmeTop:ManagedElement=SGSN-15A-WPP-V401,SgsnMmeTop:SystemFunctions=1,SgsnMmeBRM:BrM=1\"";
		// String Index_Line="identity \"1\"";
		// String MO_Type="moType SgsnMmeBRM:BrmBackupManager";
		ArrayList<String> ldn = new ArrayList<String>();
		/*
		 * System.out.println("Parent_line "+Parent_line.size());
		 * System.out.println("index_line "+index_line.size());
		 * System.out.println("MO_type "+MO_type.size());
		 */
		for (int i = 0; i < Parent_line.size(); i++) {
			// System.out.println("entered for loop");
			String Parent_Line = Parent_line.get(i);
			String Index_Line = index_line.get(i);
			String MO_Type = MO_type.get(i);
			// System.out.println("Parent_Line "+Parent_Line);
			// System.out.println("index_line "+Index_Line);
			// System.out.println("MO_Type "+MO_Type );
			String Child = "";
			int cnt = 0;
			String Parent_Required = "", P3;
			Parent_Line = Parent_Line.replaceAll("parent ", "");
			cnt = count_characters(Parent_Line, ',');

			if (Parent_Line.equals("\"\""))
				Parent_Required = "";
			else if (cnt == 0) {
				Parent_Line = Parent_Line.substring(1);
				Parent_Required = Parent_Line.substring(
						Parent_Line.indexOf(':'), Parent_Line.indexOf('"'));
				Parent_Required = Parent_Required.substring(1);
			} else {
				while (cnt > 0) {
					if (Parent_Required.equals("")) {
						Parent_Required = Parent_Required
								+ Parent_Line.substring(
										Parent_Line.indexOf(':'),
										Parent_Line.indexOf(','));
						Parent_Required = Parent_Required.substring(1);
					} else {
						P3 = Parent_Line.substring(Parent_Line.indexOf(':'),
								Parent_Line.indexOf(','));
						Parent_Required = Parent_Required + ","
								+ P3.substring(1);
					}
					Parent_Line = Parent_Line.substring(Parent_Line
							.indexOf(','));
					Parent_Line = Parent_Line.substring(1);
					cnt = cnt - 1;
				}
				P3 = Parent_Line.substring(Parent_Line.indexOf(':'),
						Parent_Line.indexOf('"'));
				Parent_Required = Parent_Required + "," + P3.substring(1);

				Parent_Line = Parent_Line.replaceAll("\"", "");
			}
			// System.out.println("Parent_Required is "+Parent_Required);

			MO_Type = MO_Type.replaceAll("moType ", "");
			MO_Type = MO_Type.substring(MO_Type.indexOf(':'));
			Child = MO_Type.substring(1);
			// System.out.println("Child "+Child);
			String Index = "";
			Index_Line = Index_Line.trim();
			Index_Line = Index_Line.replaceAll("identity ", "");
			Index = Index_Line.replaceAll("\"", "");
			String final_ldn = Parent_Required + "," + Child + "=" + Index;
			// System.out.println("ldn "+final_ldn);
			ldn.add(final_ldn);
		}
		return ldn;
	}

	public static int count_characters(String line, char ch) {
		int count = 0;

		for (int l = 0; l < line.length(); l++) {
			if (line.charAt(l) == ',')
				count++;
		}

		return count;
	}

	public ArrayList<String> get_moid_Kertyle(ArrayList<String> Parent_line,
			ArrayList<String> index_line, ArrayList<String> MO_type) {
		// String
		// Parent_Line="parent \"SgsnMmeTop:ManagedElement=SGSN-15A-WPP-V401,SgsnMmeTop:SystemFunctions=1,SgsnMmeBRM:BrM=1\"";
		// String Index_Line="identity \"1\"";
		// String MO_Type="moType SgsnMmeBRM:BrmBackupManager";
		ArrayList<String> ldn = new ArrayList<String>();

		System.out.println("Parent_line " + Parent_line.size());
		System.out.println("index_line " + index_line.size());
		System.out.println("MO_type " + MO_type.size());

		for (int i = 0; i < Parent_line.size(); i++) {
			// System.out.println("entered for loop");
			String Parent = Parent_line.get(i).replaceAll("parent ", "");
			String Parent_Line = Parent.trim();
			String Index_Line = index_line.get(i);
			String MO_Type = MO_type.get(i);
			// System.out.println("Parent_Line "+Parent_Line);
			// System.out.println("index_line "+Index_Line);
			// System.out.println("MO_Type "+MO_Type );
			String Child = "";

			String Parent_Required = "";

			Parent_Required = Parent_Line.replaceAll("\"", "");

			MO_Type = MO_Type.replaceAll("moType ", "");
			// MO_Type=MO_Type.substring(MO_Type.indexOf(':'));
			// Child=MO_Type.substring(1);
			Child = MO_Type.trim();
			// System.out.println("Child "+Child);
			String Index = "";
			Index_Line = Index_Line.trim();
			Index_Line = Index_Line.replaceAll("identity ", "");
			Index = Index_Line.replaceAll("\"", "");
			String final_ldn = Parent_Required + "," + Child + "=" + Index;
			// System.out.println("ldn "+final_ldn);
			ldn.add(final_ldn);
		}
		return ldn;
	}
}
