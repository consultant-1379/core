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

public class generate_Kertyle {

	public int write_lines(String Parent, String MO_Type, int No_of_MOs,
			int Identity, String File_Name, String Logs_File_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(File_Name, true)));
			PrintWriter out_logs = new PrintWriter(new BufferedWriter(
					new FileWriter(Logs_File_Name, true)));
			// System.out.println("Writing KERTYLE Script for  this "+MO_Type+" to populate "+No_of_MOs+" times");
			out_logs.println("Writing KERTYLE Script for  this " + MO_Type
					+ " to populate " + No_of_MOs + " times");
			for (int i = 0; i < No_of_MOs; i++) {
				out.println("CREATE");
				out.println("(");
				out.println(Parent);
				out.println("\tidentity \"" + Identity + "\"");
				out.println("\tmoType " + MO_Type);
				out.println("\texception none");
				out.println(")");
				Identity++;
			}
			out.close();
			out_logs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Identity;
	}

	public void set_mos_kertyle(String mo, ArrayList<String> Attributes,
			String File_Name, String Logs_File_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(File_Name, true)));
			PrintWriter out_logs = new PrintWriter(new BufferedWriter(
					new FileWriter(Logs_File_Name, true)));
			out_logs.println("setting mandatatory attributes for MO " + mo);
			out.println("SET");
			out.println("(");
			out.println("mo " + mo);
			out.println("exception none");
			out.println("nrOfAttributes " + Attributes.size());
			for (int i = 0; i < Attributes.size(); i++) {
				out.println(Attributes.get(i));
			}
			out.println(")");
			out.close();
			out_logs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete_mos(ArrayList<String> mos, String File_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(File_Name, true)));
			// System.out.println("Writing KERTYLE Script for  this "+MO_Type+" to populate "+No_of_MOs+" times");
			System.out.println("Writing delete KERTYLE Script");
			for (int i = 0; i < mos.size(); i++) {
				out.println("DELETE");
				out.println("(");
				out.println("  mo \"" + mos.get(i) + "\"");
				out.println(")");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
