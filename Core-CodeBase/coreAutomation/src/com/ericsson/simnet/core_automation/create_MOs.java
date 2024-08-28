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

public class create_MOs {

	public void generate_file(String[] NE_Names, String File_to_Read,
			String File_to_Write) throws IOException {
		String line = null;
		BufferedReader BR1 = null;
		try {
			// Create object of FileReader
			FileReader mo_file = new FileReader(File_to_Read);

			// Instantiate the BufferedReader Class
			BR1 = new BufferedReader(mo_file);

			// Variable to hold the one line data
			String line1 = null;
			ArrayList<String> a1 = new ArrayList<String>();

			while ((line1 = BR1.readLine()) != null) {
				a1.add(line1);
			}
			// BR1.close();
			for (int i = 1; i < (NE_Names.length); i++) {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(File_to_Write + (i + 1) + ".mo", false)));
				for (int j = 0; j < a1.size(); j++) {
					line = a1.get(j);
					out.println(line.replaceAll(NE_Names[0], NE_Names[i]));
				}
				out.close();
			}
		} catch (FileNotFoundException e) {
			System.err
					.println("Caught FileNotFoundException: in Create_MOs file "
							+ e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.err.println("Caught IOException: in Create_MOs file "
					+ e.getMessage());
		} finally {
			if (null != BR1) {
				BR1.close();
			}
		}
	}

}
