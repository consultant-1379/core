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
package com.ericsson.simnet.core.utilities;

import java.io.*;
import java.util.ArrayList;

public class CreateScript {

	public void createScript(ArrayList<String> mmlNames) {

		AbsolutePath absp = new AbsolutePath();
		try {
			File file = new File("script.sh");
			FileWriter fw = new FileWriter("script.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("#!/bin/bash");
			pw.println("add1=\"/netsim/inst/\";");
			pw.println("add2=$add1.\"/netsim_shell\";");
			pw.println("call_mmlfunction()");
			pw.println("{");
			// System.out.println("the size of mml list is "+mml_names.size());
			for (int i = 0; i < mmlNames.size(); i++) {
				// System.out.println("the size of mml list is "+mml_names.get(i));
				pw.println("echo \".send "
						+ absp.getAbsolutePath(mmlNames.get(i)) + "\"");
			}
			pw.println("}");
			pw.println("(");
			pw.println("call_mmlfunction");
			pw.println(") | $add2");
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
