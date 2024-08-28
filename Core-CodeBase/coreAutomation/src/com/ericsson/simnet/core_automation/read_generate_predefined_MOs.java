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
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class read_generate_predefined_MOs {

	ArrayList<String> MO_Type_generated = new ArrayList<String>();
	ArrayList<Integer> MO_Count_generated = new ArrayList<Integer>();

	public void Read_MOs(String SIM_Name, int No_of_MOs, String mim_file_name,
			String Generated_Kertyle_File_Name, String Logs_File_Name,
			String[] NE_Names) {
		ArrayList<String> MO_Count = new ArrayList<String>();
		ArrayList<String> MO_Type = new ArrayList<String>();
		ArrayList<Integer> MO_Count_for_each_type = new ArrayList<Integer>();
		Properties configfile = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream("MOs_Config.properties");
			configfile.load(input);

			for (int i = 0; i < No_of_MOs; i++) {
				MO_Type.add(configfile.getProperty("Enter_MO_Type" + (i + 1)));
				MO_Count.add(configfile.getProperty("Ener_No_of_MOs_of_Type"
						+ (i + 1)));
				MO_Count_for_each_type.add(Integer.parseInt(MO_Count.get(i)));
			}
			Get_MOs_xml(SIM_Name, mim_file_name, Generated_Kertyle_File_Name,
					Logs_File_Name, NE_Names, MO_Type, MO_Count_for_each_type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Get_MOs_xml(String SIM_Name, String xml_File_Name,
			String Generated_Kertyle_File_Name, String Logs_File_Name,
			String[] NE_Names, ArrayList<String> MO_Type,
			ArrayList<Integer> MO_Count_for_each_type) throws IOException {

		ArrayList<String> MO_Names = new ArrayList<String>();
		ArrayList<String> MO_Parent_String = new ArrayList<String>();
		ArrayList<String> MO_Child = new ArrayList<String>();
		ArrayList<Integer> MOs_min = new ArrayList<Integer>();
		ArrayList<Integer> MOs_max = new ArrayList<Integer>();

		ArrayList<String> MO_Type_Selected = new ArrayList<String>();
		ArrayList<Integer> MO_Type_Cardinality = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_max = new ArrayList<Integer>();
		ArrayList<Integer> No_of_available_MOs = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_tobe_Populated = new ArrayList<Integer>();
		ArrayList<String> MO_Parent_String_Selected = new ArrayList<String>();

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xml_File_Name);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("relationship");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if ((eElement.getElementsByTagName("parent").item(0) != null)
							&& (eElement.getElementsByTagName("child").item(0) != null)) {
						if (eElement.getElementsByTagName("min").item(0) != null) {
							MO_Names.add(eElement.getAttribute("name"));

							MOs_min.add(Integer.parseInt(eElement
									.getElementsByTagName("min").item(0)
									.getTextContent()));
							if (eElement.getElementsByTagName("max").item(0) != null) {
								if (eElement.getElementsByTagName("max")
										.item(0).getTextContent().length() > 8)
									MOs_max.add(80000);
								else
									MOs_max.add(Integer.parseInt(eElement
											.getElementsByTagName("max")
											.item(0).getTextContent()));
							} else
								MOs_max.add(80000);
						}
					}
				}
			}

			for (int i = 0; i < MO_Names.size(); i++) {

				if ((MO_Names.get(i).contains("_to_"))
						|| (MO_Names.get(i).contains("_To_"))) {
					String[] str = MO_Names.get(i).split("(?i)_to_");
					MO_Parent_String.add(str[0]);
					String[] str2 = str[0].split(":");
					if (str[1].contains(":"))
						MO_Child.add(str[1]);
					else
						MO_Child.add(str2[0] + ":" + str[1]);

					No_of_available_MOs.add(MOs_max.get(i) - MOs_min.get(i));
					// No_of_available_MOs.add(MOs_max.get(i));
				}
			}
			System.out.println("completed getting MOs from xml");
			for (int m = 0; m < MO_Type.size(); m++) {
				int k = 0;
				// this loop is added by considering this relationship tag
				// <relationship
				// name="SgsnMmeTop:SystemFunctions_to_SgsnMmeSwIM:SwInventory">
				for (k = 0; k < MO_Child.size(); k++) {
					String[] str = MO_Child.get(k).split(":");
					String check = (str[0] + ":" + MO_Type.get(m));

					if (MO_Child.get(k).toLowerCase()
							.equals(check.toLowerCase())
							&& str[1].toLowerCase().equals(
									MO_Type.get(m).toLowerCase())) {
						MO_Type_Selected.add(MO_Child.get(k));
						MO_Type_Cardinality.add(No_of_available_MOs.get(k));
						MO_Type_max.add(MOs_max.get(k));
						MO_Parent_String_Selected.add(MO_Parent_String.get(k));
						MO_Type_tobe_Populated.add(MO_Count_for_each_type
								.get(m));
						break;
					}
				}
				if (k == MO_Child.size()) {
					int l;
					for (l = 0; l < MO_Child.size(); l++) {
						if (MO_Child.get(l).toLowerCase()
								.contains(MO_Type.get(m).toLowerCase())) {
							System.out.println("MO_Type.get(m) "
									+ MO_Type.get(m) + " MO_Child.get(l)"
									+ MO_Child.get(l));
							MO_Type_Selected.add(MO_Child.get(l));
							MO_Type_Cardinality.add(No_of_available_MOs.get(l));
							MO_Type_max.add(MOs_max.get(l));
							MO_Parent_String_Selected.add(MO_Parent_String
									.get(l));
							MO_Type_tobe_Populated.add(MO_Count_for_each_type
									.get(m));
							break;
						}
					}
					if (l == MO_Child.size()) {
						System.out
								.println("WARN:cannot able to find out this MO "
										+ MO_Type.get(m)
										+ " in the relationship tags in the xml file");
						System.out
								.println("WARN:proceeding for other MOs with out populating this. populate this MO manually if required ");
					}
				}
			}
			if (MO_Type_Selected.size() == 0) {
				// System.exit(0);
				System.out
						.println("you have enterd the count of MOs as \"0\". So, assuming that you want a simulation with out .mogenerator generate we are proceeding");
			} else {
				divide_MOs_hierarchy(SIM_Name, Generated_Kertyle_File_Name,
						Logs_File_Name, NE_Names, MO_Type_Selected,
						MO_Type_Cardinality, MO_Type_tobe_Populated,
						MO_Type_max, MO_Parent_String_Selected);
				Del_exs_MOs del_MOs = new Del_exs_MOs();
				del_MOs.read_all_MOs(SIM_Name, NE_Names, NE_Names[0]
						+ "_orig.mo", "Kertyle_aft_gen_6.mo",
						MO_Type_generated, MO_Count_generated, Logs_File_Name);

				// NE_Names[0]+"_orig.mo
				// Kertyle_aft_gen_6.mo
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void divide_MOs_hierarchy(String SIM_Name,
			String Generated_Kertyle_File_Name, String Logs_File_Name,
			String[] NE_Names, ArrayList<String> MO_Type_Selected,
			ArrayList<Integer> MO_Type_Cardinality,
			ArrayList<Integer> MO_Type_tobe_Populated,
			ArrayList<Integer> MO_Type_MAX,
			ArrayList<String> MO_Parent_String_Selected) throws IOException {
		ArrayList<String> MO_Parent_Line_Selected = new ArrayList<String>();

		// System.out.println("Generate_MOs");
		Kertyle_read read = new Kertyle_read();
		create_MOs cre_mo = new create_MOs(); // creating object for Create_MOs

		MO_Parent_Line_Selected = read.Get_Parent(Generated_Kertyle_File_Name,
				MO_Type_Selected);
		int Identity = 1234, count = 0;
		boolean MOs_available_in_gen = true;

		ArrayList<String> Parent_gen_0 = new ArrayList<String>();
		ArrayList<String> MO_Type_0 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_0 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_0 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_1 = new ArrayList<String>();
		ArrayList<String> MO_Type_1 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_1 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_1 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_2 = new ArrayList<String>();
		ArrayList<String> MO_Type_2 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_2 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_2 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_3 = new ArrayList<String>();
		ArrayList<String> MO_Type_3 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_3 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_3 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_4 = new ArrayList<String>();
		ArrayList<String> MO_Type_4 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_4 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_4 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_5 = new ArrayList<String>();
		ArrayList<String> MO_Type_5 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_5 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_5 = new ArrayList<Integer>();
		ArrayList<String> Parent_gen_6 = new ArrayList<String>();
		ArrayList<String> MO_Type_6 = new ArrayList<String>();
		ArrayList<Integer> MO_Count_Type_6 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_Cardinality_6 = new ArrayList<Integer>();
		ArrayList<String> MO_parent_string_0 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_1 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_2 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_3 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_4 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_5 = new ArrayList<String>();
		ArrayList<String> MO_parent_string_6 = new ArrayList<String>();
		ArrayList<Integer> MO_Type_MAX_0 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_1 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_2 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_3 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_4 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_5 = new ArrayList<Integer>();
		ArrayList<Integer> MO_Type_MAX_6 = new ArrayList<Integer>();
		for (int k = 0; k < MO_Parent_Line_Selected.size(); k++) {
			if (MO_Parent_Line_Selected.get(k).equals(null)) {
				System.out.println("MO_Parent_Line_Selected.get(k) "
						+ MO_Parent_Line_Selected.get(k));
				System.out.println("MO_Type_Selected.get(k) "
						+ MO_Type_Selected.get(k));
				System.out.println("MO_Type_tobe_Populated.get(k) "
						+ MO_Type_tobe_Populated.get(k));
				System.out.println("MO_Parent_String_Selected.get(k) "
						+ MO_Parent_String_Selected.get(k));
				System.out.println("parent line selected is null for this mo "
						+ MO_Type_Selected.get(k));
				break;
			} else
				count = count_characters(MO_Parent_Line_Selected.get(k), ',');

			if (count == 0 && !(MO_Parent_Line_Selected.get(k).equals(null))) {
				Parent_gen_0.add(MO_Parent_Line_Selected.get(k));
				MO_Type_0.add(MO_Type_Selected.get(k));
				MO_Count_Type_0.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_0.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_0.add(MO_Type_MAX.get(k));
				MO_parent_string_0.add(MO_Parent_String_Selected.get(k));
			} else if (count == 1) {
				Parent_gen_1.add(MO_Parent_Line_Selected.get(k));
				MO_Type_1.add(MO_Type_Selected.get(k));
				MO_Count_Type_1.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_1.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_1.add(MO_Type_MAX.get(k));
				MO_parent_string_1.add(MO_Parent_String_Selected.get(k));
			} else if (count == 2) {
				Parent_gen_2.add(MO_Parent_Line_Selected.get(k));
				MO_Type_2.add(MO_Type_Selected.get(k));
				MO_Count_Type_2.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_2.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_2.add(MO_Type_MAX.get(k));
				MO_parent_string_2.add(MO_Parent_String_Selected.get(k));
			} else if (count == 3) {
				Parent_gen_3.add(MO_Parent_Line_Selected.get(k));
				MO_Type_3.add(MO_Type_Selected.get(k));
				MO_Count_Type_3.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_3.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_3.add(MO_Type_MAX.get(k));
				MO_parent_string_3.add(MO_Parent_String_Selected.get(k));
			} else if (count == 4) {
				Parent_gen_4.add(MO_Parent_Line_Selected.get(k));
				MO_Type_4.add(MO_Type_Selected.get(k));
				MO_Count_Type_4.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_4.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_4.add(MO_Type_MAX.get(k));
				MO_parent_string_4.add(MO_Parent_String_Selected.get(k));
			} else if (count == 5) {
				Parent_gen_5.add(MO_Parent_Line_Selected.get(k));
				MO_Type_5.add(MO_Type_Selected.get(k));
				MO_Count_Type_5.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_5.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_5.add(MO_Type_MAX.get(k));
				MO_parent_string_5.add(MO_Parent_String_Selected.get(k));
			} else {
				Parent_gen_6.add(MO_Parent_Line_Selected.get(k));
				MO_Type_6.add(MO_Type_Selected.get(k));
				MO_Count_Type_6.add(MO_Type_tobe_Populated.get(k));
				MO_Type_Cardinality_6.add(MO_Type_Cardinality.get(k));
				MO_Type_MAX_6.add(MO_Type_MAX.get(k));
				MO_parent_string_6.add(MO_Parent_String_Selected.get(k));
			}
		}

		System.out.println("No of MOs in paren gen 0 is " + MO_Type_0.size());
		System.out.println("No of MOs in paren gen 1 is " + MO_Type_1.size());
		System.out.println("No of MOs in paren gen 2 is " + MO_Type_2.size());
		System.out.println("No of MOs in paren gen 3 is " + MO_Type_3.size());
		System.out.println("No of MOs in paren gen 4 is " + MO_Type_4.size());
		System.out.println("No of MOs in paren gen 5 is " + MO_Type_5.size());
		System.out.println("No of MOs in paren gen 6 is " + MO_Type_6.size());

		generate_mml_Kertyle load_gen_ker = new generate_mml_Kertyle();
		// for parent gen_size=0
		String init_kertyle_file_name = NE_Names[0] + "_orig.mo";
		{
			MOs_available_in_gen = generate_MOs(init_kertyle_file_name,
					Logs_File_Name, Parent_gen_0, MO_Type_0, MO_Count_Type_0,
					MO_Type_Cardinality_0, MO_Type_MAX_0, MO_parent_string_0,
					Identity, "Kertyle_parent_def_0_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_0_", "Kertyle_aft_gen_0.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_0_1.mo",
						"Kertyle_parent_def_0_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				copy_File(init_kertyle_file_name, "Kertyle_aft_gen_0.mo");
			}
		}

		// for parent gen_size=1

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_0.mo",
					Logs_File_Name, Parent_gen_1, MO_Type_1, MO_Count_Type_1,
					MO_Type_Cardinality_1, MO_Type_MAX_1, MO_parent_string_1,
					Identity, "Kertyle_parent_def_1_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_1_", "Kertyle_aft_gen_1.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_1_1.mo",
						"Kertyle_parent_def_1_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_0.mo", "Kertyle_aft_gen_1.mo");
			}
		}

		// for parent gen_size=2

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_1.mo",
					Logs_File_Name, Parent_gen_2, MO_Type_2, MO_Count_Type_2,
					MO_Type_Cardinality_2, MO_Type_MAX_2, MO_parent_string_2,
					Identity, "Kertyle_parent_def_2_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_2_", "Kertyle_aft_gen_2.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_2_1.mo",
						"Kertyle_parent_def_2_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_1.mo", "Kertyle_aft_gen_2.mo");
			}
		}

		// for parent gen_size=3

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_2.mo",
					Logs_File_Name, Parent_gen_3, MO_Type_3, MO_Count_Type_3,
					MO_Type_Cardinality_3, MO_Type_MAX_3, MO_parent_string_3,
					Identity, "Kertyle_parent_def_3_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_3_", "Kertyle_aft_gen_3.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_3_1.mo",
						"Kertyle_parent_def_3_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_2.mo", "Kertyle_aft_gen_3.mo");
			}
		}

		// for parent gen_size=4

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_3.mo",
					Logs_File_Name, Parent_gen_4, MO_Type_4, MO_Count_Type_4,
					MO_Type_Cardinality_4, MO_Type_MAX_4, MO_parent_string_4,
					Identity, "Kertyle_parent_def_4_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_4_", "Kertyle_aft_gen_4.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_4_1.mo",
						"Kertyle_parent_def_4_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_3.mo", "Kertyle_aft_gen_4.mo");
			}
		}

		// for parent gen_size=5

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_4.mo",
					Logs_File_Name, Parent_gen_5, MO_Type_5, MO_Count_Type_5,
					MO_Type_Cardinality_5, MO_Type_MAX_5, MO_parent_string_5,
					Identity, "Kertyle_parent_def_5_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_5_", "Kertyle_aft_gen_5.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_5_1.mo",
						"Kertyle_parent_def_5_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_4.mo", "Kertyle_aft_gen_5.mo");
			}
		}

		// for parent gen_size=6

		{
			MOs_available_in_gen = generate_MOs("Kertyle_aft_gen_5.mo",
					Logs_File_Name, Parent_gen_6, MO_Type_6, MO_Count_Type_6,
					MO_Type_Cardinality_6, MO_Type_MAX_6, MO_parent_string_6,
					Identity, "Kertyle_parent_def_6_1.mo",
					init_kertyle_file_name);

			if (MOs_available_in_gen) {
				load_gen_ker.Kertyle_mml_MOs_def(SIM_Name, NE_Names,
						"Kertyle_parent_def_6_", "Kertyle_aft_gen_6.mo");

				cre_mo.generate_file(NE_Names, "Kertyle_parent_def_6_1.mo",
						"Kertyle_parent_def_6_"); // writing kertyle files for
													// all nodes just by
													// replacing node name from
													// the initial kertyle
													// generated

				create_run_script(SIM_Name, NE_Names.length, Logs_File_Name);
			} else {
				rename_file("Kertyle_aft_gen_5.mo", "Kertyle_aft_gen_6.mo");
			}
		}
	}

	public boolean generate_MOs(String system_Generated_Kertyle_File_Name,
			String Logs_File_Name, ArrayList<String> MO_Parent,
			ArrayList<String> MO_Child, ArrayList<Integer> MO_Type_count,
			ArrayList<Integer> MO_Type_cardinality,
			ArrayList<Integer> MO_TYPE_MAX, ArrayList<String> MO_Prnt_stg,
			int Identity, String generated_kertyle_filename,
			String init_Kertyle_file) {
		generate_Kertyle ker = new generate_Kertyle();
		Kertyle_read ker_read = new Kertyle_read();
		int MO_Type_Count = 0;
		boolean MOs_available = false;
		// ArrayList<String> MO_Parent_Line_latest = new ArrayList<String>();

		// MO_Parent_Line_latest=ker_read.Get_Parent(system_Generated_Kertyle_File_Name,
		// MO_Child);
		// System.out.println("MO_Child.size() is "+MO_Child.size());

		if (MO_Child.size() == 0) {
			// System.out.println("generating empty file");
			// Identity=ker.write_lines("","",0, 1234,
			// generated_kertyle_filename, Logs_File_Name);
			MOs_available = false;
		} else {
			MOs_available = true;
			for (int j = 0; j < MO_Child.size(); j++) {
				if (MO_Parent.get(j).equals(null)) {
					System.out.println("we cannnot populate this "
							+ MO_Child.get(j));
					Identity = ker.write_lines("", "", 0, 1234,
							generated_kertyle_filename, Logs_File_Name);
				}

				else {
					MO_Type_Count = ker_read
							.Get_indi_MO_Count(
									system_Generated_Kertyle_File_Name,
									MO_Child.get(j));
					/*
					 * if(MO_Child.get(j).contains("Sgsn_Mme:DnsServerAddress"))
					 * {
					 * System.out.println("MO_Type_count.get(j) "+MO_Type_count
					 * .get(j));
					 * System.out.println("MO_Type_cardinality.get(j) "
					 * +MO_Type_cardinality.get(j));
					 * System.out.println("MO_Parent.get(j) "+MO_Parent.get(j));
					 * System.out.println("MO_Child.get(j) "+MO_Child.get(j)); }
					 */
					if (MO_Type_Count == 0) {
						if (MO_Type_count.get(j) <= MO_TYPE_MAX.get(j)) {
							Identity = ker.write_lines(MO_Parent.get(j),
									MO_Child.get(j), MO_Type_count.get(j), 1,
									generated_kertyle_filename, Logs_File_Name);
							MO_Type_generated.add(MO_Child.get(j));
							MO_Count_generated.add(MO_Type_count.get(j));
							// System.out.println("we are generating this MO "+MO_Child.get(j)+"from index 1 with count "+MO_Type_count.get(j));
						} else {
							Identity = generate_MOs_splcase(
									system_Generated_Kertyle_File_Name,
									Logs_File_Name, MO_Parent.get(j),
									MO_Child.get(j), MO_Type_count.get(j),
									MO_Type_cardinality.get(j),
									MO_Prnt_stg.get(j), 1,
									generated_kertyle_filename);
						}
					}

					else {
						if (MO_Type_Count < MO_Type_count.get(j)) {
							int no_of_MOs_to_be_populated = (MO_Type_count
									.get(j) - MO_Type_Count);

							if (MO_Type_count.get(j) <= MO_TYPE_MAX.get(j)) {
								Identity = ker.write_lines(MO_Parent.get(j),
										MO_Child.get(j),
										no_of_MOs_to_be_populated, 143,
										generated_kertyle_filename,
										Logs_File_Name);

								int no_of_MOs_init = ker_read
										.Get_indi_MO_Count(init_Kertyle_file,
												MO_Child.get(j));
								if (no_of_MOs_init == MO_Type_Count) {
									MO_Type_generated.add(MO_Child.get(j));
									MO_Count_generated
											.add(no_of_MOs_to_be_populated);
								} else {
									MO_Type_generated.add(MO_Child.get(j));
									MO_Count_generated.add(MO_Type_count.get(j)
											- no_of_MOs_init);
								}
							} else {
								// System.out.println("MO_TYPE_MAX.get(j) "+MO_TYPE_MAX.get(j));
								// System.out.println("MO_Type_cardinality.get(j) "+MO_Type_cardinality.get(j));
								Identity = generate_MOs_splcase(
										system_Generated_Kertyle_File_Name,
										Logs_File_Name, MO_Parent.get(j),
										MO_Child.get(j), MO_Type_count.get(j),
										MO_Type_cardinality.get(j),
										MO_Prnt_stg.get(j), 143,
										generated_kertyle_filename);
							}
						} else {

							int no_of_MOs_init = ker_read.Get_indi_MO_Count(
									init_Kertyle_file, MO_Child.get(j)); // initial
																			// count
																			// of
																			// MOs
																			// in
																			// file

							MO_Type_generated.add(MO_Child.get(j));
							MO_Count_generated.add((MO_Type_count.get(j))
									- no_of_MOs_init);

							// System.out.println("This MO "+MO_Child.get(j)+" is already present with count greater than or equal to requested count");
							// System.out.println("this MO"+MO_Child.get(j)+" present on the NE "+MO_Type_Count+" times  and req count is "+MO_Type_count.get(j)+" times"+
							// " So, not generating this MO");
							Identity = ker.write_lines(MO_Parent.get(j),
									MO_Child.get(j), 0, 1234,
									generated_kertyle_filename, Logs_File_Name);

						}
					}

				}
			}
		}

		// System.out.println("MO_Type_genrated size is "+MO_Type_generated.size());
		// System.out.println("MO_Count_generated size is "+MO_Count_generated.size());
		// return Identity;
		return MOs_available;
	}

	// public int generate_MOs(String system_Generated_Kertyle_File_Name, String
	// Logs_File_Name, ArrayList<String>
	// MO_Parent,ArrayList<String>MO_Child,ArrayList<Integer>
	// MO_Type_count,ArrayList<Integer> MO_Type_cardinality,ArrayList<String>
	// MO_Prnt_stg, int Identity, String generated_kertyle_filename)
	// Identity=generate_MOs_splcase(system_Generated_Kertyle_File_Name,
	// Logs_File_Name, MO_Parent.get(j), MO_Child.get(j), MO_Type_count.get(j),
	// MO_Type_cardinality.get(j), MO_Prnt_stg.get(j),Identity,
	// generated_kertyle_filename);
	public int generate_MOs_splcase(String system_Generated_Kertyle_File_Name,
			String Logs_File_Name, String parent, String child, int count_req,
			int cardinality, String parent_string, int Identity,
			String generated_kertyle_filename) {
		Kertyle_read ker_read = new Kertyle_read();
		generate_Kertyle ker = new generate_Kertyle();

		ArrayList<String> MO_parent_names = new ArrayList<String>();
		ArrayList<String> MO_parent_Line_new = new ArrayList<String>();
		System.out.println("entered special case loop");
		//
		MO_parent_names = ker_read.Get_all_MO_Names(
				system_Generated_Kertyle_File_Name, parent_string);
		System.out.println("MO Parent names size " + MO_parent_names.size());

		String parent_to_add = parent.substring(0, parent.lastIndexOf("="));
		for (int k = 0; k < MO_parent_names.size(); k++) {
			// System.out.println("MO_Parent_Names "+(k+1)+" is "+MO_parent_names.get(k));
			String tmp = parent_to_add + "=" + MO_parent_names.get(k) + "\"";
			// System.out.println("parent name modified = "+tmp);
			MO_parent_Line_new.add(tmp);
		}
		//
		int no_of_MOs_to_be_added = count_req;
		int same_MO_Count = ker_read.Get_indi_MO_Count(
				system_Generated_Kertyle_File_Name, parent_string);
		// System.out.println("same_MO_Count "+same_MO_Count);
		// System.out.println("cardinality "+cardinality);
		// System.out.println("MO_Type_count.get(j) "+count_req);
		if (((cardinality * (same_MO_Count)) >= count_req)) {

			int l = 0;
			while (no_of_MOs_to_be_added > 0) {
				if (no_of_MOs_to_be_added >= cardinality) {
					Identity = ker.write_lines(MO_parent_Line_new.get(l),
							child, cardinality, Identity,
							generated_kertyle_filename, Logs_File_Name);

					MO_Type_generated.add(child);
					MO_Count_generated.add(cardinality);

					// System.out.println("we are generating this MO "+child+" with count "+cardinality);

					no_of_MOs_to_be_added = no_of_MOs_to_be_added
							- (cardinality);
					// System.out.println("no_of_MOs_to_be_added "+no_of_MOs_to_be_added);
					l++;
				} else {
					// System.out.println("MO_Child.get(k) "+MO_Child.get(k));
					// System.out.println("MO_Type.get(m)) "+MO_Type.get(m));
					Identity = ker.write_lines(MO_parent_Line_new.get(l),
							child, no_of_MOs_to_be_added, Identity,
							generated_kertyle_filename, Logs_File_Name);
					MO_Type_generated.add(child);
					MO_Count_generated.add(no_of_MOs_to_be_added);
					// System.out.println("we are generating this MO "+child+" with count "+no_of_MOs_to_be_added);
					l++;

					no_of_MOs_to_be_added = 0;
				}
			}
		} else {
			System.out
					.println("WARN:cannot populate this MO "
							+ child
							+ " as the cardinality for this MO is \"+MO_Type_Cardinality.get(m)+\" less than what you requested to be populated \"+MO_Type_tobe_Populated.get(m)");
			System.out.println("generating empty file");
			Identity = ker.write_lines("", "", 0, 1234,
					generated_kertyle_filename, Logs_File_Name);

		}

		return Identity;
	}

	public void create_run_script(String SIM_Name, int no_of_MOs,
			String Logs_File_Name) throws IOException {
		ArrayList<String> mml_names = new ArrayList<String>();

		// calling to write MOFiles to script
		// generate_mml_kertyle_indi ker_gen=new generate_mml_kertyle_indi();
		call_Script call = new call_Script(); // creating Call_Script object
		exec_Script exec = new exec_Script();

		// ker_gen.Kertyle_mml(SIM_Name, NE_Names);
		for (int i = 0; i < no_of_MOs; i++) {
			mml_names.add("mml_MOs_" + (i + 1) + ".mml");
		}
		call.build_script_parallel(mml_names);
		mml_names.removeAll(mml_names);

		File newFile = new File(Logs_File_Name);
		FileWriter fw = new FileWriter(newFile, true);
		System.out.println("INFO: Generating MOs parallely...................");

		String[] output_mos = new String[no_of_MOs];
		// output_mos=ex.exec_script(NE_Names.length);
		output_mos = exec.exec_command_parallel(no_of_MOs);
		for (int c = 0; c < no_of_MOs; c++) {
			fw.write(output_mos[c]);
		}

		fw.close();
	}

	public int count_characters(String line, char ch) {
		int count = 0;

		for (int l = 0; l < line.length(); l++) {
			if (line.charAt(l) == ',')
				count++;
		}

		return count;
	}

	public void rename_file(String old_file, String new_file) {
		File file_old = new File(old_file);
		File file_new = new File(new_file);
		file_old.renameTo(file_new);
	}

	private static void copy_File(String old_file, String new_file)
			throws IOException {
		InputStream is = null;
		OutputStream os = null;
		File file_old = new File(old_file);
		File file_new = new File(new_file);

		try {
			is = new FileInputStream(file_old);
			os = new FileOutputStream(file_new);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
}
