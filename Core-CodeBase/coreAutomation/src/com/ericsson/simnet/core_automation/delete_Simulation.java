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

public class delete_Simulation {

	public void del_sim(String SIM_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_del_sim.mml", true)));
			out.println(".deletesimulation " + SIM_Name + " force");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
