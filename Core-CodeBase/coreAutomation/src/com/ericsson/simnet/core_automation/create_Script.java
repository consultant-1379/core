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

public class create_Script {

	public void showIPs_script() {
		try {
			File file = new File("showIPs.sh");
			FileWriter fw = new FileWriter("showIPs.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("LOCALIPADDRESS=`hostname | nslookup | grep -i Address | grep -v '#' | awk -F: '{print $2}' | tr -d ' '`;");
			pw.println("ifconfig -a | grep -i \"inet \" | awk '{print $2}' | awk -F: '{print $2}' | sort -ut. -k1,1 -k2,2n -k3,3n -k4,4n | grep -v \"127.0.0.1\" | grep -v \"^$LOCALIPADDRESS$\" > dat/ipListIPv4.txt");
			pw.println("ifconfig -a | grep -i \"inet6 \" | awk '{print $3}' | sort -ut: | grep -v \"::1/128\"> dat/ipListIPv6.txt");
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void checkUsedIps_script() {
		try {
			File file = new File("checkUsedIps.sh");
			FileWriter fw = new FileWriter("checkUsedIps.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("#!/bin/sh");
			pw.println("PWD=`pwd`");
			pw.println("echo \".show allsimnes\" | ~/inst/netsim_shell | awk -F\" \" '{print $2}' | sed '/^$/d' | grep -v \"[a-z][A-Z]*\"  | awk -F\":\" '{print $1}'| grep -v \":\" | sed '/^NE/d' > dat/dumpUsedIps_Intermediate.txt");
			pw.println("");
			pw.println("cat dat/dumpUsedIps_Intermediate.txt | awk -F\",\" '{print $1}'  > dat/dumpUsedIps.txt");
			pw.println("");
			pw.println("cat dat/dumpUsedIps_Intermediate.txt | awk -F\",\" '{print $2}' | sed '/^$/d' >> dat/dumpUsedIps.txt");
			pw.println("");
			pw.println("cat dat/dumpUsedIps_Intermediate.txt | awk -F\",\" '{print $3}' | sed '/^$/d' >> dat/dumpUsedIps.txt");
			pw.println("");
			pw.println("rm dat/dumpUsedIps_Intermediate.txt");
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void swroot_script() {
		try {
			File file = new File("swroot.sh");
			FileWriter fw = new FileWriter("swroot.sh");
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("echo \"shroot\" | sudo -S sh showIPs.sh");
			pw.println("echo \"enterd root\"");
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
