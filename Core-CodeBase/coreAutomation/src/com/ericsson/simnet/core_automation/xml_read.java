package com.ericsson.simnet.core_automation;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

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

public class xml_read {

	public ArrayList<String> Get_MOs(String SIM_Name, int Required_MOs,
			String NE_Name, String NE_Type, String xml_File_Name,
			String Logs_File_Name) throws IOException {
		// public static void main(String[] args) throws
		// IndexOutOfBoundsException

		// int Required_MOs=40000;
		// String NE_Name="mgw";
		String Kertyle_File_Name = NE_Name + ".mo";
		ArrayList<String> MO_Names = new ArrayList<String>();
		ArrayList<String> MO_Parent_String = new ArrayList<String>();
		ArrayList<String> MO_Child = new ArrayList<String>();
		ArrayList<Integer> MOs_min = new ArrayList<Integer>();
		ArrayList<Integer> MOs_max = new ArrayList<Integer>();
		ArrayList<Integer> No_of_available_MOs = new ArrayList<Integer>();
		ArrayList<String> Back_up_MOs = new ArrayList<String>();
		ArrayList<String> MO_Names_obsolete = new ArrayList<String>();

		/*
		 * Get_xml_Name xml_name=new Get_xml_Name(); String
		 * xml_File_Name=xml_name.get_name(NE_Type);
		 */
		try {

			// File fXmlFile = new
			// File("/netsim/inst/zzzuserinstallation/mim_files/"+xml_File_Name);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// Document doc = dBuilder.parse(fXmlFile);
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

			NodeList nList2 = doc.getElementsByTagName("class");

			for (int temp = 0; temp < nList2.getLength(); temp++) {

				Node nNode = nList2.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (eElement.getElementsByTagName("obsolete").item(0) != null) {
						MO_Names_obsolete.add(eElement.getAttribute("name"));
					}

				}
			}

			for (int i = 0; i < MO_Names.size(); i++) {
				if ((MO_Names.get(i).contains("_to_"))
						|| (MO_Names.get(i).contains("_To_"))) {
					String[] str = MO_Names.get(i).split("(?i)_to_");
					MO_Parent_String.add(str[0]);
					String[] str2 = str[0].split(":");

					if (NE_Type.toLowerCase().contains("rbs")) {
						MO_Child.add(str[1]);
					} else {
						if (str[1].contains(":"))
							MO_Child.add(str[1]);
						else
							MO_Child.add(str2[0] + ":" + str[1]);
					}

					No_of_available_MOs.add(MOs_max.get(i) - MOs_min.get(i));
				}
			}
			/*
			 * for(int i=0;i<MO_Names_obsolete.size();i++) {
			 * System.out.println((
			 * i+1)+" MO_Names_obsolete "+MO_Names_obsolete.get(i)); }
			 * System.out.println("MO_Child.size() before "+MO_Child.size());
			 * System
			 * .out.println("MO_Parent_String.size() before "+MO_Parent_String
			 * .size());
			 * System.out.println("No_of_available_MOs.size() before "+
			 * No_of_available_MOs.size());
			 */

			System.out.println("INFO: No of obsolete MOs found = "
					+ MO_Names_obsolete.size());
			if (MO_Names_obsolete.size() == 0)
				System.out
						.println("INFO: No problem of  obsolete MOs for this NE");
			else
				System.out.println("INFO: Not populating these "
						+ MO_Names_obsolete.size() + " obsolete MOs");

			// for(int k=0;k<MO_Child.size();k++)
			for (int l = 0; l < MO_Names_obsolete.size(); l++) {
				// for(int l=0;l<MO_Names_obsolete.size();l++)
				for (int k = 0; k < MO_Child.size(); k++) {

					if (MO_Child.get(k).equals(MO_Names_obsolete.get(l))) {
						// System.out.println("k before "+k);

						MO_Child.remove(k);
						No_of_available_MOs.remove(k);
						MO_Parent_String.remove(k);
						// System.out.println("MO_Child.size() between "+MO_Child.size());
						// System.out.println("k after "+k);

						break;
					}
				}
			}

			// System.out.println("MO_Child.size() after "+MO_Child.size());
			// System.out.println("MO_Parent_String.size() after "+MO_Parent_String.size());
			// System.out.println("No_of_available_MOs.size() after "+No_of_available_MOs.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// calling decision making class to decide which MO to be populated and
		// how many times
		get_MOs_count get = new get_MOs_count();
		/*
		 * System.out.println("MO_Parent_String size = "+MO_Parent_String.size())
		 * ; System.out.println("MO_Child = "+MO_Child.size()); for(int
		 * i=0;i<MO_Child.size();i++) {
		 * System.out.println("MO_Parent_String= "+MO_Parent_String.get(i));
		 * System.out.println("MO_Child = "+MO_Child.get(i)); }
		 */
		Back_up_MOs = get.divide(SIM_Name, MO_Child, MO_Parent_String,
				No_of_available_MOs, Required_MOs, Kertyle_File_Name,
				Logs_File_Name);
		return Back_up_MOs;
	}
}
