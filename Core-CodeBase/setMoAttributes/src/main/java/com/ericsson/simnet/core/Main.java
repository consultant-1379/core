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
package com.ericsson.simnet.core;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import com.ericsson.simnet.core.kertayle.KertayleOperations;
import com.ericsson.simnet.core.utilities.*;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out
					.println("Proper Usage is: java -jar setAttributesSgsn.jar <simulationName> <NoOfNodes> <baseName>");
			System.exit(0);
		}

		String simName = args[0];
		int numOfNodes = Integer.parseInt(args[1]);
		String baseName = args[2];
		String[] neNames = new String[numOfNodes];
		ArrayList<String> mmlNames = new ArrayList<String>();

		DeleteFiles del = new DeleteFiles();
		KertayleOperations ker = new KertayleOperations();
		CreateScript cre = new CreateScript();
		ExecuteCommand exe = new ExecuteCommand();
		AbsolutePath absp = new AbsolutePath();

		// creating logs file name with time stamp
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String timeStamp = format.format(new Date());
		String logFileName = "setmo_" + timeStamp + "_logs.txt";

		for (int i = 0; i < numOfNodes; i++) {
			if (i < 9) {
				neNames[i] = baseName + "0" + (i + 1);
			} else {
				neNames[i] = baseName + +(i + 1);
			}
		}

		// deleting runtime generated files before start
		del.checkDeleteifexists("ker_gen.mml");
		del.checkDeleteifexists("ker_load.mml");
		del.checkDeleteifexists("script.sh");
		for (int i = 0; i < neNames.length; i++) {
			del.checkDeleteifexists(neNames[i] + ".mo");
			del.checkDeleteifexists(neNames[i] + "_load.mo");
		}

		ker.generateKerOutMml(simName, "ker_gen.mml", neNames);
		mmlNames.add("ker_gen.mml");
		cre.createScript(mmlNames);
		mmlNames.removeAll(mmlNames);
		String output_1 = exe.executeCommand("./script.sh");

		File newFile = new File(logFileName); // getting logs
		FileWriter fw = new FileWriter(newFile, true);
		fw.write(output_1);
		fw.close();

		FileOperations fileop = new FileOperations();
		ArrayList<String> attributeFile = new ArrayList<String>();
		if (args[0].contains("SGSN")) {
			attributeFile = fileop.readFile(absp
					.getAbsolutePath("sgsnAttributeData.csv"));
		}
		if (args[0].contains("CSCF")) {
			attributeFile = fileop.readFile(absp
					.getAbsolutePath("cscfAttributeData.csv"));
		}

		ArrayList<String> attribute = new ArrayList<String>();
		ArrayList<String> dataType = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();
		ArrayList<String> freq = new ArrayList<String>();

		for (int z = 0; z < attributeFile.size(); z++) {
			if (!(attributeFile.get(z)
					.contains("attribute name,data type,values,frequencyType"))) {
				String[] attributeData = attributeFile.get(z).split(",");
				attribute.add(attributeData[0]);
				dataType.add(attributeData[1]);
				value.add(attributeData[2]);
				freq.add(attributeData[3]);
			}
		}

		for (int j = 0; j < neNames.length; j++) {
			ArrayList<String> file = new ArrayList<String>();
			file = fileop.readFile(absp.getAbsolutePath(neNames[(j)] + ".mo"));
			// chk.findAttributes(file, "mobileCountryCode", "String", "353",
			// NE_Names[j]);
			for (int k = 0; k < attribute.size(); k++) {
				fileop.findAttributesInFile(file, attribute.get(k),
						dataType.get(k), value.get(k), freq.get(k), neNames[j]);
			}
		}

		ker.loadKertayleMml(simName, "ker_load.mml", neNames);
		mmlNames.add("ker_load.mml");
		cre.createScript(mmlNames);
		mmlNames.removeAll(mmlNames);
		String output_2 = exe.executeCommand("./script.sh");

		File logFile = new File(logFileName); // getting logs
		FileWriter fw2 = new FileWriter(logFile, true);
		fw2.write(output_2);
		fw2.close();

		del.checkDeleteifexists("ker_gen.mml");
		del.checkDeleteifexists("ker_" + "load.mml");
		del.checkDeleteifexists("script.sh");
		for (int i = 0; i < neNames.length; i++) {
			del.checkDeleteifexists(neNames[i] + ".mo");
			del.checkDeleteifexists(neNames[i] + "_load.mo");
		}

	}
}
