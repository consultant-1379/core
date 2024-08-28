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

public class call_Script {

	public void build_script(ArrayList<String> mml_names) {
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
			for (int i = 0; i < mml_names.size(); i++) {
				pw.println("echo \".send " + get_addr(mml_names.get(i)) + "\"");
			}
			pw.println("}");
			pw.println("(");
			pw.println("call_mmlfunction");
			pw.println(") | $add2");
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void build_script_parallel(ArrayList<String> mml_names) {
		try {
			for (int i = 0; i < mml_names.size(); i++) {
				File file = new File("script" + (i + 1) + ".sh");
				FileWriter fw = new FileWriter("script" + (i + 1) + ".sh");
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
				PrintWriter pw = new PrintWriter(fw);

				pw.println("#!/bin/bash");
				pw.println("add1=\"/netsim/inst/\";");
				pw.println("add2=$add1.\"/netsim_shell\";");
				pw.println("call_mmlfunction()");
				pw.println("{");
				pw.println("echo \".send " + get_addr(mml_names.get(i)) + "\"");

				pw.println("}");
				pw.println("(");
				pw.println("call_mmlfunction");
				pw.println(") | $add2");
				pw.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
