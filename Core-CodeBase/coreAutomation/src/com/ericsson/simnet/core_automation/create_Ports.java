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

class create_Ports {

	String lineAddPort, lineConfigPort, lineAddDD, lineConfigDD, DD_Name,
			Port_Name;

	int[] createAddress = new int[3];

	public void buildPort(String lineAddPort, String lineConfigPort,
			String Port_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_ports.mml", true)));
			out.println(".select configuration");
			out.println(lineAddPort);
			out.println(lineConfigPort);
			// out.println(".config port override "+Port_Name+" ssh_port 2022");
			out.println(".config save");
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buildDD(String lineAddDD, String lineConfigDD,
			String Default_Destination_Name, String host_Name) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("mml_ports.mml", true)));
			out.println(".select configuration");
			out.println(lineAddDD);
			out.println(".config external servers " + DD_Name + " " + host_Name);
			out.println(lineConfigDD);
			out.println(".config save");
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String[] select_ports(String protocol, String Node_Name,
			String createDefaultDestination, String host_Name) {
		// Port_Name="p:"+Node_Name;
		// DD_Name = "DD_"+Node_Name+":"+createDefaultDestination;
		createAddress[0] = 192;
		createAddress[1] = 168;
		createAddress[2] = 100;

		if (protocol == "SGSN") {
			Port_Name = "SGSN";
			DD_Name = "SGSN" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " netsimwpp "
					+ host_Name;
			lineConfigPort = ".config port address " + Port_Name + " "
					+ createAddress[0] + "." + createAddress[1] + "."
					+ createAddress[2] + ".0 4001";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " netsimwpp";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "NETCONF_PROT") {
			Port_Name = "NETCONF_PROT";
			DD_Name = "NETCONF_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " netconf_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 2 %unique 1 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " netconf_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "NETCONF_PROT_TLS") {
			Port_Name = "NETCONF_PROT_TLS";
			DD_Name = "NETCONF_PROT_TLS" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " netconf_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 2 %unique 2 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " netconf_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "IIOP_PROT") {
			Port_Name = "IIOP_PROT";
			DD_Name = "IIOP_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " iiop_prot "
					+ host_Name;
			lineConfigPort = ".config port address " + Port_Name + " nehttpd "
					+ createAddress[0] + "." + createAddress[1] + "."
					+ createAddress[2] + ".0 56834 56836 no_value";
			buildPort(lineAddPort, lineConfigPort, Port_Name);
		} else if (protocol == "STN_PROT") {
			Port_Name = "STN_PROT";
			DD_Name = "STN_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " snmp_ssh_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 2 %unique %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " snmp_ssh_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "SGSN_PROT") {
			Port_Name = "SGSN_PROT";
			DD_Name = "SGSN_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " sgsn_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 2 %unique 4001 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " sgsn_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "SGSN_SSH_PROT") {

		} else if (protocol == "APGTCP") {
			Port_Name = "APGTCP";
			DD_Name = "APGTCP" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " apgtcp "
					+ host_Name;
			lineConfigPort = ".config port address " + Port_Name + " "
					+ createAddress[0] + "." + createAddress[1] + "."
					+ createAddress[2] + ".0 5000 5022 23";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " apgtcp";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 50000 50010";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "APG_APGTCP") {
			Port_Name = "APG_APGTCP";
			DD_Name = "APG_APGTCP" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " apg_apgtcp "
					+ host_Name;
			lineConfigPort = ".config port address " + Port_Name + " "
					+ createAddress[0] + "." + createAddress[1] + "."
					+ createAddress[2] + ".0 5000 5022 5023";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " apg_apgtcp";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 50000 50010";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "MSC_S_CP") {
			Port_Name = "MSC_S_CP";
			DD_Name = "MSC_S_CP" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " msc-s_cp_prot "
					+ host_Name;
			lineConfigPort = ".config port address force_no_valu " + Port_Name;
			buildPort(lineAddPort, lineConfigPort, Port_Name);
		}

		else if (protocol == "NETCONF_PROT_SSH") {
			Port_Name = "NETCONF_PROT_SSH";
			DD_Name = "NETCONF_PROT_SSH" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " netconf_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 community 3 %unique 3 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " netconf_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "SNMP_SSH_PROT") {
			Port_Name = "SNMP_SSH_PROT";
			DD_Name = "SNMP_SSH_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " snmp_ssh_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1|2 %unique %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " snmp_ssh_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "SNMP_SSH_FTP_PROT") {
			Port_Name = "SNMP_SSH_FTP_PROT";
			DD_Name = "SNMP_SSH_FTP_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name
					+ " snmp_ssh_ftp_prot " + host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1 %unique %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name
					+ " snmp_ssh_ftp_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "SNMP") {
			Port_Name = "SNMP";
			DD_Name = "SNMP" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " snmp "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1 %unique %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " snmp";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "TSP_PROT") {
			Port_Name = "TSP_PROT";
			DD_Name = "TSP_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " tsp_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1 %unique 7423 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " tsp_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "TSP_SSH_PROT") {
			Port_Name = "TSP_SSH_PROT";
			DD_Name = "TSP_SSH_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " tsp_ssh_prot "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1 %unique 7423 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " tsp_ssh_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}

		else if (protocol == "HTTP_HTTPS_PORT") {
			Port_Name = "HTTP_HTTPS_PORT";
			DD_Name = "HTTP_HTTPS_PORT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name + " http_https_port "
					+ host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1 %unique 7423 %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " http_https_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "SNMP_TELNET_PROT") {
			Port_Name = "SNMP_TELNET_PROT";
			DD_Name = "SNMP_TELNET_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name
					+ " snmp_telnet_prot " + host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1|2|3 %unique no_value %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name + " snmp_telnet_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "SNMP_SSH_TELNET_PROT") {
			Port_Name = "SNMP_SSH_TELNET_PROT";
			DD_Name = "SNMP_SSH_TELNET_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name
					+ " snmp_ssh_telnet_prot " + host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 1161 public 1|2|3 %unique no_value %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name
					+ " snmp_ssh_telnet_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "LANSWITCH_PROT") {
			Port_Name = "LANSWITCH_PROT";
			DD_Name = "LANSWITCH_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name
					+ " https_http_snmp_ssh " + host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 161 public 1|2|3 %unique no_value %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name
					+ " https_http_snmp_ssh";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 1";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		} else if (protocol == "MLPT_PROT") {
			Port_Name = "MLPT_PROT";
			DD_Name = "MLPT_PROT" + createDefaultDestination;
			lineAddPort = ".config add port " + Port_Name
					+ " xrpc_snmp_ssh_http_prot " + host_Name;
			lineConfigPort = ".config port address "
					+ Port_Name
					+ " "
					+ createAddress[0]
					+ "."
					+ createAddress[1]
					+ "."
					+ createAddress[2]
					+ ".0 161 public 1|2|3 %unique no_value %simname_%nename authpass privpass 2 2";
			buildPort(lineAddPort, lineConfigPort, Port_Name);

			lineAddDD = ".config add external " + DD_Name
					+ " xrpc_snmp_ssh_http_prot";
			lineConfigDD = ".config external address " + DD_Name + " "
					+ createDefaultDestination + " 162 2";
			buildDD(lineAddDD, lineConfigDD, DD_Name, host_Name);
		}
		return new String[] { Port_Name, DD_Name };
	}

}
