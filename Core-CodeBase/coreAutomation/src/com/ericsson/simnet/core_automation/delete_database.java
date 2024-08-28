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

public class delete_database {

	public void delete_db(String SIM_Name, String NE_Name) {
		try {

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_del_db_ne.mml", true)));
			out.println(".open " + SIM_Name);
			out.println(".select network");
			out.println(".stop");
			out.println(".deletenedatabase");
			out.println(".select network");
			out.println(".start");
			out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
					+ get_addr(NE_Name + "_orig" + ".mo") + "\";");
			out.println(".stop");
			out.println(".set save");

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
