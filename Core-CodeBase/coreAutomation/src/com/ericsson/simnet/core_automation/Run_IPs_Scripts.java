package com.ericsson.simnet.core_automation;

import java.io.File;
import java.io.IOException;

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

public class Run_IPs_Scripts {

	public void check_directory() {
		String[] direc = new String[2];
		direc[0] = "dat";
		direc[1] = "Automation_Logs";
		for (int i = 0; i < 2; i++) {
			File theDir = new File(direc[i]);
			if (!theDir.exists()) {
				System.out.println("INFO: creating directory: " + direc[i]);
				boolean result = false;

				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					if (i == 0)
						System.out
								.println("INFO: "
										+ direc[i]
										+ " Directory created to store free IPs files generated ");
					else
						System.out
								.println("INFO: "
										+ direc[i]
										+ " Directory created to store LOG files generated ");
				}
			} else {
				System.out.println("INFO: Directory " + direc[i] + " is there");
			}
		}
		File f1 = new File("showIPs.sh");
		File f2 = new File("checkUsedIps.sh");
		File f3 = new File("swroot.sh");
		if (f1.exists() && f2.exists() && f3.exists())
			System.out.println("INFO: All scripts required are available");
		else {
			System.out
					.println("INFO: All Scripts required are not available, we are creating them");
			create_Script create_sh = new create_Script();

			if (!(f1.exists())) {
				System.out.println("INFO: creating script showIPs.sh");
				create_sh.showIPs_script();
			}
			if (!(f2.exists())) {
				System.out.println("INFO: creating script checkUsedIps.sh");
				create_sh.checkUsedIps_script();
			}
			if (!(f3.exists())) {
				System.out.println("INFO: creating script swroot.sh");
				create_sh.swroot_script();
			}
			// System.exit(0);
		}

	}

	public void Total_Ips() throws InterruptedException {
		try {
			Process p = Runtime.getRuntime().exec("sh swroot.sh");
			Thread.sleep(3000);
			p.waitFor();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void Used_Ips() throws InterruptedException {
		try {

			Process q = Runtime.getRuntime().exec("sh checkUsedIps.sh");
			Thread.sleep(3000);
			q.waitFor();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
