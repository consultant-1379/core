package com.ericsson.simnet.core_automation;

import java.io.*;

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

public class generate_mml_Kertyle {

	public void Kertyle_mml(String SIM_Name, String[] NE_Names) {
		String[] Kertyle_Names = new String[NE_Names.length];

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mml", true)));
			out.println(".open " + SIM_Name);
			for (int i = 0; i < NE_Names.length; i++) {
				Kertyle_Names[i] = "Kertyle" + (i + 1) + ".mo";
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				out.println("kertayle:file=\"" + get_addr(Kertyle_Names[i])
						+ "\";");
				out.println(".sleep 60");
				out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
						+ get_addr(NE_Names[(i)] + "_final0.mo") + "\";");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Kertyle_mml_MOs_check(String SIM_Name, String NE_Name,
			String kertyle_Name, String File_Name, int l) {

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(File_Name, false)));
			out.println(".open " + SIM_Name);
			out.println(".select " + NE_Name);
			out.println(".start");
			out.println("kertayle:file=\"" + get_addr(kertyle_Name) + "\";");
			out.println(".sleep 60");

			out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
					+ get_addr(NE_Name + "_final" + l + ".mo") + "\";");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Kertyle_mml_indi(String SIM_Name, String[] NE_Names) {
		String[] Kertyle_Names = new String[NE_Names.length];

		try {
			for (int i = 0; i < NE_Names.length; i++) {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter("mml_MOs_" + (i + 1) + ".mml", true)));

				out.println(".open " + SIM_Name);
				Kertyle_Names[i] = "Kertyle" + (i + 1) + ".mo";
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				out.println("kertayle:file=\"" + get_addr(Kertyle_Names[i])
						+ "\";");
				out.println(".sleep 60");
				out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
						+ get_addr(NE_Names[(i)] + "_final0.mo") + "\";");

				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Kertyle_mml_MOs_def(String SIM_Name, String[] NE_Names,
			String Kertyle_to_load, String Kertyle_to_gen) {
		String[] Kertyle_Names = new String[NE_Names.length];

		try {
			for (int i = 0; i < NE_Names.length; i++) {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter("mml_MOs_" + (i + 1) + ".mml", false)));
				out.println(".open " + SIM_Name);
				Kertyle_Names[i] = Kertyle_to_load + (i + 1) + ".mo";
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				out.println("kertayle:file=\"" + get_addr(Kertyle_Names[i])
						+ "\";");
				out.println(".sleep 60");

				if (i == 0) {
					out.println(".start");
					out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
							+ get_addr(Kertyle_to_gen) + "\";");
					out.println(".sleep 60");

				}
				out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Kertyle_mml_MOs_del(String SIM_Name, String[] NE_Names) {
		// String ldn_new;
		try {
			for (int i = 0; i < NE_Names.length; i++) {
				String Kertyle_Name = "kertyle_del_" + (i + 1) + ".mo";
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new FileWriter("mml_MOs_del_"
								+ (i + 1) + ".mml", false)));
				out.println(".open " + SIM_Name);
				out.println(".select " + NE_Names[i]);
				out.println(".start");

				out.println("kertayle:file=\"" + get_addr(Kertyle_Name) + "\";");
				out.println(".sleep 60");

				out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Kertyle_mml_script_MGw(String SIM_Name, String[] NE_Names) {

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_MOs.mml", true)));
			out.println(".open " + SIM_Name);

			// Kertyle_Names[i]="Kertyle"+(i+1)+".mo";
			out.println(".select network");
			out.println(".start");
			out.println("kertayle:file=\"" + get_addr("mml_MOs.mo") + "\";");
			out.println(".sleep 60");

			for (int i = 0; i < (NE_Names.length); i++) {
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				set_mand_attribute_MGw(NE_Names[i], (i + 1));
				out.println("kertayle:file=\""
						+ get_addr("set_mos" + (i + 1) + ".mo") + "\";");
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set_mand_attribute_MGw(String NE_Name, int number) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("set_mos" + number + ".mo", true)));
			System.out
					.println("INFO: setting userlabel for Managed Element of "
							+ NE_Name);
			out.println("SET");
			out.println("(");
			out.println("mo \"ManagedElement=1\"");
			out.println("exception none");
			out.println("nrOfAttributes 1");
			out.println("\"userLabel\" String \"" + NE_Name + "\"");
			out.println(")");

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get_addr(String name) {
		String Path;
		File directory = new File(name);
		boolean isDirectory = directory.isDirectory();
		if (isDirectory) {
			Path = directory.getAbsolutePath();
		} else {
			Path = directory.getAbsolutePath();
		}

		return Path;

	}
}
