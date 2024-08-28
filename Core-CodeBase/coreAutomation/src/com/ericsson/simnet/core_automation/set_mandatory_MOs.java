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

public class set_mandatory_MOs {

	public boolean setMO(String SIM_Name, String NE_Type, String[] NE_Names,
			String Logs_File_Name) throws IndexOutOfBoundsException {
		String attribute_to_set;
		boolean var = false;
		ArrayList<String> attributes_set = new ArrayList<String>();
		String mo;
		generate_Kertyle kert = new generate_Kertyle();
		String Filename = NE_Names[0] + ".mo";
		if (NE_Type.toLowerCase().contains("dsc 14a-wcdma-1-ndp-1-3-1-0")) {
			System.out
					.println("Setting attribute value for MeasurementTypeRef for node"
							+ NE_Type);
			setattr(Filename, NE_Type, SIM_Name, NE_Names);
			// Create_Kertyle(NE_Names);
			Create_mos(SIM_Name, NE_Names);
			var = true;
		} else if ((NE_Type.toLowerCase().contains("upg"))
				|| (NE_Type.toLowerCase().contains("pgm"))) {
			System.out
					.println("Setting attribute value for MeasurementTypeRef for node "
							+ NE_Type);
			setattr(Filename, NE_Type, SIM_Name, NE_Names);
			set_mos_upg_pgm(SIM_Name, NE_Names);
			var = true;
		} else if ((NE_Type.toLowerCase().contains("dua-s"))
				|| (NE_Type.toLowerCase().contains("dsc"))
				|| (NE_Type.toLowerCase().contains("esapc"))) {
			System.out
					.println("Setting attribute value for MeasurementTypeRef for  all nodes of type "
							+ NE_Type);
			setattr(Filename, NE_Type, SIM_Name, NE_Names);
			var = true;
		}

		else if (NE_Type.toLowerCase().contains("bbsc")) {
			attribute_to_set = "setmoattribute:mo=\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1,MeasurementReader=1\", attributes=\"measurementSpecification= [\\\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1\\\", \\\"\\\"]\";";
			set_attr_mml(SIM_Name, NE_Names, attribute_to_set);
			var = true;

			// set_attr_man attr=new set_attr_man();
			// var=attr.set_mos(SIM_Name, NE_Type, NE_Names, Logs_File_Name);
		} else if ((NE_Type.toLowerCase().contains("sbg"))
				&& (NE_Type.toLowerCase().contains("core"))) {
			attribute_to_set = "setmoattribute:mo=\"ManagedElement="
					+ NE_Names[0]
					+ ",SystemFunctions=1,Pm=1,PmJob=1,MeasurementReader=1\", attributes=\"measurementSpecification= [\\\"ManagedElement="
					+ NE_Names[0]
					+ ",SystemFunctions=1,Pm=1,PmJob=1\\\", \\\"\\\"]\";";
			set_attr_mml(SIM_Name, NE_Names, attribute_to_set);
			var = true;
		} else if ((NE_Type.toLowerCase().contains("sgsn"))
				&& (NE_Type.toLowerCase().contains("wpp"))) {
			attribute_to_set = "setmoattribute:mo=\"ManagedElement="
					+ NE_Names[0]
					+ ",SystemFunctions=1,Pm=1,PmJob=1,MeasurementReader=1\", attributes=\"measurementSpecification= [\\\"ManagedElement="
					+ NE_Names[0]
					+ ",SystemFunctions=1,Pm=1,PmJob=1\\\", \\\"\\\"]\";";
			set_attr_mml(SIM_Name, NE_Names, attribute_to_set);
			var = true;
		} else if (NE_Type.toLowerCase().contains("sdnc-p")) {
			for (int i = 0; i < NE_Names.length; i++) {
				String Parent, MO;

				Parent = "parent \"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,CmwPm:Pm=1\"";
				MO = "CmwPm:PmGroup";
				kert.write_lines(Parent, MO, 1, 1, "set_mos" + (i + 1) + ".mo",
						Logs_File_Name); // creates mandatory mo CmwPm:PmGroup

				Parent = "parent \"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,CmwPm:Pm=1,CmwPm:PmGroup=sdncPMGroupMim\"";
				MO = "CmwPm:MeasurementType";
				kert.write_lines(Parent, MO, 1, 2, "set_mos" + (i + 1) + ".mo",
						Logs_File_Name); // creates mandatory mo
											// CmwPm:MeasurementType

				mo = "\"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSecM:SecM=1,ComSecM:UserManagement=1,ComLocalAuthorization:LocalAuthorizationMethod=1,ComLocalAuthorization:CustomRule=1\"";
				attributes_set.add("\"ruleData\" String \"\"");
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
				attributes_set.removeAll(attributes_set); // sets the attributes
															// to null

				mo = "\"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSnmp:Snmp=1,ComSnmp:SnmpTargetV1=1\"";
				attributes_set.add("\"community\" String \"\"");
				attributes_set.add("\"address\" String \"\"");
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
				attributes_set.removeAll(attributes_set); // sets the attributes
															// to null

				mo = "\"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSnmp:Snmp=1,ComSnmp:SnmpTargetV2C=1\"";
				attributes_set.add("\"community\" String \"\"");
				attributes_set.add("\"address\" String \"\"");
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
				attributes_set.removeAll(attributes_set); // sets the attributes
															// to null

				mo = "\"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSnmp:Snmp=1,ComSnmp:SnmpTargetV3=1\"";
				attributes_set.add("\"user\" String \"\"");
				attributes_set.add("\"address\" String \"\"");
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
				attributes_set.removeAll(attributes_set); // sets the attributes
															// to null

				mo = "\"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ComSysM:SysM=1,ComSysM:NtpServer=1\"";
				attributes_set.add("\"serverAddress\" String \"\"");
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
				attributes_set.removeAll(attributes_set); // sets the attributes
															// to null

			}
			create_mos_mml(SIM_Name, NE_Names);

			attribute_to_set = "setmoattribute:mo=\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1,MeasurementReader=1\", attributes=\"measurementSpecification= [\\\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1\\\", \\\"\\\"]\";";
			set_attr_mml(SIM_Name, NE_Names, attribute_to_set);
			var = true;
		} else if (NE_Type.toLowerCase().contains("esasn")) {
			mo = "\"ComTop:ManagedElement=1\"";
			attributes_set
					.add("\"dnPrefix\" String \"SubNetwork=ONRM_ROOT_MO_R,SubNetwork=IMS,MeContext="
							+ NE_Names[0] + "\"");
			for (int i = 0; i < NE_Names.length; i++) {
				kert.set_mos_kertyle(mo, attributes_set, "set_mos" + (i + 1)
						+ ".mo", Logs_File_Name);
			}
			create_mos_mml(SIM_Name, NE_Names);
			attribute_to_set = "setmoattribute:mo=\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1,MeasurementReader=1\", attributes=\"measurementSpecification= [\\\"ManagedElement=1,SystemFunctions=1,Pm=1,PmJob=1\\\", \\\"\\\"]\";";
			set_attr_mml(SIM_Name, NE_Names, attribute_to_set);
			var = true;
		} else {
			var = false;
		}
		return var;
	}

	public void setattr(String FileName, String NE_Type, String SIM_Name,
			String[] NE_Names) throws IndexOutOfBoundsException {
		try {
			FileReader get_mo_file = new FileReader(FileName);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(get_mo_file);

			ArrayList<String> get_MO_Parent = new ArrayList<String>();
			// Getting list of parents
			get_MO_Parent = get_Parent(FileName);

			// Creating mml file for setting attribute values
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_set_mos.mml", true)));
			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".start -parallel");

			for (int i = 0; i < NE_Names.length; i++) {
				out.println(".select " + NE_Names[i]);
				for (int k = 0; k < get_MO_Parent.size(); k++) {
					String Get_NE_Name = NE_Names[0];
					String First_Attr = get_MO_Parent.get(k).replaceAll(
							Get_NE_Name, NE_Names[i]);
					String First_String = First_Attr.substring(
							First_Attr.indexOf("\"") + 1,
							First_Attr.indexOf(","));
					String Actual_Value = "[\\\"\\\",\\\"" + First_String
							+ "\\\"]";
					String Actual_Parent = get_MO_Parent.get(k)
							.replaceAll("parent", "").replaceAll("ComTop:", "")
							.replaceAll("ECIM_PM:", "")
							.replaceAll("CmwPm:", "")
							.replaceAll(Get_NE_Name, NE_Names[i]);

					out.println("setmoattribute:mo=" + Actual_Parent
							+ ",attributes=\"measurementSpecification="
							+ Actual_Value + "\";");
				}
			}
			out.close();
			BR1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> get_Parent(String FileName) {
		ArrayList<String> MO_Parent = new ArrayList<String>();
		try {

			// Create object of FileReader
			FileReader mo_file = new FileReader(FileName);

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(mo_file);

			// Variable to hold the one line data
			String line1 = null;
			ArrayList<String> a1 = new ArrayList<String>();
			int sa1 = 0;
			int Count = 0;
			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);
				if (line1.contains("measurementTypeRef"))
					Count++;
			}

			BR1.close();
			sa1 = a1.size();

			String MOAttr = "PmThresholdMonitoring";
			for (int i = 0; i < Count; i++) {
				int j = 0;
				for (j = 2; j < sa1; j++) {
					if ((a1.get(j)).contains(MOAttr)) {
						MO_Parent.add(a1.get((j - 2)));
						a1.remove(j);
						a1.add(j, "TEST");
						break;
					}
				}
				if (j == sa1)
					MO_Parent.add(null);
			}
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:"
					+ e.getMessage());
		}

		return MO_Parent;
	}

	public void Create_mos(String SIM_Name, String[] NE_Names) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_create_mos.mml", true)));
			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".start -parallel");
			for (int i = 0; i < NE_Names.length - 1; i++) {
				Create_Kertyle(NE_Names[i]);
				out.println(".select " + NE_Names[i]);
				out.println("kertayle:file=\""
						+ get_addr("mml_create_mos" + NE_Names[i] + ".mo")
						+ "\";");
				// out.println(".stop");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Create_Kertyle(String NE_Names) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_create_mos" + NE_Names + ".mo", true)));
			out.println("CREATE");
			out.println("(");
			out.println("parent \"ComTop:ManagedElement=1,ComTop:SystemFunctions=1\"");
			out.println("identity \"1\"");
			out.println("moType ECIM_BrM:BrM");
			out.println("exception none");
			out.println("nrOfAttributes 1");
			out.println("\"brMId\" String \"1\"");
			out.println(")");
			out.println("CREATE");
			out.println("(");
			out.println("parent \"ComTop:ManagedElement=1,ComTop:SystemFunctions=1,ECIM_BrM:BrM=1\"");
			out.println("identity \"1\"");
			out.println("moType ECIM_BrM:BrmBackupManager");
			out.println("exception none");
			out.println("nrOfAttributes 3");
			out.println("\"brmBackupManagerId\" String \"1\"");
			out.println("\"backupType\" String \"Type\"");
			out.println("\"backupDomain\" String \"Domain\"");
			out.println(")");
			out.println("SET");
			out.println("(");
			out.println("mo \"ComTop:ManagedElement=1\"");
			out.println("exception none");
			out.println("nrOfAttributes 1");
			out.println("\"managedElementId\" String \"" + NE_Names + "\"");
			out.println(")");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set_mos_upg_pgm(String SIM_Name, String[] NE_Names) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_create_mos.mml", true)));
			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".start -parallel");
			for (int i = 0; i < NE_Names.length; i++) {
				Create_Kertyle_name(NE_Names[i]);
				out.println(".select " + NE_Names[i]);
				out.println("kertayle:file=\""
						+ get_addr("mml_set_name_mos_" + NE_Names[i] + ".mo")
						+ "\";");
				// out.println(".stop");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Create_Kertyle_name(String NE_Names) {
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter("mml_set_name_mos_"
							+ NE_Names + ".mo", true)));
			out.println("SET");
			out.println("(");
			out.println("mo \"ComTop:ManagedElement=" + NE_Names + "\"");
			out.println("exception none");
			out.println("nrOfAttributes 1");
			out.println("\"managedElementId\" String \"1\"");
			out.println(")");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set_attr_mml(String SIM_Name, String[] NE_Names,
			String attribute_to_set) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_set_mos.mml", true)));
			String attribute;
			out.println(".open " + SIM_Name);
			for (int i = 0; i < (NE_Names.length); i++) {
				attribute = attribute_to_set.replaceAll(NE_Names[0],
						NE_Names[i]);
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				out.println(attribute);
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void create_mos_mml(String SIM_Name, String[] NE_Names) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_set_mos.mml", true)));
			out.println(".open " + SIM_Name);
			for (int i = 0; i < (NE_Names.length); i++) {
				out.println(".select " + NE_Names[i]);
				out.println(".start");
				out.println("kertayle:file=\""
						+ get_addr("set_mos" + (i + 1) + ".mo") + "\";");
				out.println(".stop");

			}
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
