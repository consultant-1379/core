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

public class assign_Port {
	int j = 0;

	public void assign_IP(String SIM_Name, String[] NE_Names, String Port_Name,
			int No_of_Nodes, String[] new_IP) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_assnports.mml", true)));
			out.println(".open " + SIM_Name);
			for (int i = 0; i < No_of_Nodes; i++) {
				out.println(".selectnocallback " + NE_Names[i]);
				out.println(".modifyne checkselected .set port " + Port_Name
						+ " port");
				out.println(".set port " + Port_Name);
				out.println(".modifyne set_subaddr " + new_IP[j]
						+ " subaddr no_value");
				out.println(".set taggedaddr subaddr " + new_IP[j] + " 1");
				out.println(".set save");
				j++;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void assign_DD(String SIM_Name, int No_of_Nodes, String[] NE_Names,
			String createDefaultDestinationName) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_assndd.mml", true)));
			out.println(".open " + SIM_Name);
			for (int i = 0; i < No_of_Nodes; i++) {
				out.println(".selectnocallback " + NE_Names[i]);
				out.println(".modifyne checkselected external "
						+ createDefaultDestinationName + " default destination");
				out.println(".set external " + createDefaultDestinationName);
				out.println(".set save");
			}
			out.println(".selectnocallback network");
			out.println(".arneconfig rootmo ONRM_ROOT_MO ");
			out.println();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
