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
import java.util.*;

public class Get_Free_IPs {
	public ArrayList<String> get_ips() {

		ArrayList<String> t_ips = new ArrayList<String>();
		ArrayList<String> u_ips = new ArrayList<String>();
		ArrayList<String> f_ips = new ArrayList<String>();
		try {

			// Create object of FileReader
			FileReader Total_IP = new FileReader("dat/ipListIPv4.txt");
			FileReader Used_IP = new FileReader("dat/dumpUsedIps.txt");

			// Instantiate the BufferedReader Class
			BufferedReader BR1 = new BufferedReader(Total_IP);
			BufferedReader BR2 = new BufferedReader(Used_IP);

			// Variable to hold the one line data
			String line1, line2;

			while ((line1 = BR1.readLine()) != null) {
				t_ips.add(line1);
			}

			while ((line2 = BR2.readLine()) != null) {
				u_ips.add(line2);
			}

			BR1.close();
			BR2.close();
			int t_l, u_l, i, j;
			t_l = t_ips.size();
			u_l = u_ips.size();

			for (i = 0; i < t_l; i++) {
				for (j = 0; j < u_l; j++) {
					if (t_ips.get(i).equals(u_ips.get(j)))
						break;
				}
				if (j == u_l) {
					f_ips.add(t_ips.get(i));
				}
			}

		} catch (Exception e) {
			System.out
					.println("Error while reading file line by line:Get_Free_IPs"
							+ e.getMessage());
		}
		return f_ips;
	}
}
