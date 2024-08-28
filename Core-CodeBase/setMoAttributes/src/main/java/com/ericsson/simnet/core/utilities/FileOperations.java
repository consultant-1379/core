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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.ericsson.simnet.core.kertayle.KertayleOperations;

public class FileOperations {

	public ArrayList<String> readFile(String fileName) {
		ArrayList<String> fileContent = new ArrayList<String>();
		try {
			FileReader dumpmotree = new FileReader(fileName);
			BufferedReader BR = new BufferedReader(dumpmotree);
			String line1 = null;
			while ((line1 = BR.readLine()) != null) {
				fileContent.add(line1);
			}
			BR.close();
		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Read_Present_No_of_MOs"
							+ e.getMessage());
		}
		return fileContent;
	}

	public void findAttributesInFile(ArrayList<String> fileContent,
			String attributeName, String dataType, String value,
			String frequency, String nodeName) {
		ArrayList<String> parentLine = new ArrayList<String>();
		ArrayList<String> newParentLine = new ArrayList<String>();
		ArrayList<String> moType = new ArrayList<String>();
		ArrayList<String> identity = new ArrayList<String>();
		for (int i = 0; i < fileContent.size(); i++) {
			if (fileContent.get(i).contains("\"" + attributeName + "\"")) {
				int j;
				for (j = i; j > 0; j--) {
					if (fileContent.get(j).contains("CREATE")) {
						break;
					}
				}

				for (int k = i; k >= j; k--) {
					if (fileContent.get(k).contains("nrOfElements")) {
						boolean isAttribute = isAttribute(k, i,
								fileContent.get(k));
						if (!(isAttribute)) {
							break;
						}
					} else if (fileContent.get(k).contains("parent")) {
						String parentline = fileContent.get(k)
								.replaceAll("parent", "").replaceAll("\"", "")
								.replaceAll(" ", "");
						parentLine.add(parentline);
					} else if (fileContent.get(k).contains("identity")) {
						String fileIdentity = fileContent.get(k)
								.replaceAll("identity ", "")
								.replaceAll("\"", "").replaceAll(" ", "");
						identity.add(fileIdentity);
					} else if (fileContent.get(k).contains("moType")) {
						String fileMoType = fileContent.get(k)
								.replaceAll("moType ", "").replaceAll(" ", "");
						moType.add(fileMoType);
					} else {

					}
				}

			}
		}

		for (int m = 0; m < parentLine.size(); m++) {
			newParentLine.add(parentLine.get(m) + "," + moType.get(m) + "="
					+ identity.get(m));
		}
		String attributeFileName = nodeName + "_load.mo";
		KertayleOperations ker = new KertayleOperations();
		ker.createKertayle(attributeFileName, newParentLine, attributeName,
				dataType, value, frequency);
	}

	public boolean isAttribute(int elementsLine, int attrLine,
			String attributeLine) {
		int nrOfElements = Integer.parseInt(attributeLine.replaceAll(
				"nrOfElements ", "").replaceAll(" ", ""));
		if ((attrLine - elementsLine) <= nrOfElements)
			return false;
		else
			return true;
	}
}
