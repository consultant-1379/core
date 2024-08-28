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
package com.ericsson.simnet.core.kertayle;

import com.ericsson.simnet.core.utilities.AbsolutePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KertayleOperations {
	AbsolutePath absp = new AbsolutePath();

	public void generateKerOutMml(String simName, String fileName,
			String[] neNames) {
		String[] kertyleNames = new String[neNames.length];
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName, false)));
			out.println(".open " + simName);
			for (int i = 0; i < neNames.length; i++) {
				kertyleNames[i] = ("Kertyle" + (i + 1) + ".mo");
				out.println(".select " + neNames[i]);
				out.println("dumpmotree:moid=\"1\",ker_out,outputfile=\""
						+ this.absp.getAbsolutePath(new StringBuilder(String
								.valueOf(neNames[i])).append(".mo").toString())
						+ "\";");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadKertayleMml(String simName, String fileName,
			String[] neNames) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName, false)));
			out.println(".open " + simName);
			for (int i = 0; i < neNames.length; i++) {
				out.println(".select " + neNames[i]);
				out.println("kertayle:file=\""
						+ this.absp.getAbsolutePath(new StringBuilder(String
								.valueOf(neNames[i])).append("_load.mo")
								.toString()) + "\";");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createKertayle(String fileName, ArrayList<String> parentLines,
			String attributeName, String dataType, String value,
			String frequency) {
		try {

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName, true)));
			int integerVal = 0, strAddress0 = 0, strAddress1 = 0, strAddress2 = 0, strAddress3 = 0;
			if (frequency.equals("integer")) {
				integerVal = Integer.parseInt(value);
			}
			if (frequency.equals("ip")) {
				String[] strAddress = value.split("\\.");
				strAddress0 = Integer.parseInt(strAddress[0]);
				strAddress1 = Integer.parseInt(strAddress[1]);
				strAddress2 = Integer.parseInt(strAddress[2]);
				strAddress3 = Integer.parseInt(strAddress[3]);
			}

			for (int j = 0; j < parentLines.size(); j++) {

				out.println("SET");
				out.println("(");
				out.println("mo \"" + (String) parentLines.get(j) + "\"");
				out.println(" nrOfAttributes 1");

				if (frequency.equals("constant")) {
					out.println(attributeName + " " + dataType + " " + value
							+ " ");
				}

				else if (frequency.equals("integer")) {
					out.println(attributeName + " " + dataType + " "
							+ integerVal + " ");
					integerVal++;
				}

				else if (frequency.equals("ip")) {
					out.println(attributeName + " " + dataType + " "
							+ strAddress0 + "." + strAddress1 + "."
							+ strAddress2 + "." + strAddress3 + " ");
					strAddress3++;

					if (strAddress3 >= 255) {
						strAddress3 = 0;
						strAddress2++;
					}
				}

				out.println(")");

			}
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}