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

public class Write_MOs_Data {

	public void Write_MOs_to_config(int No_of_MOs)
	{
		try {
		   // int No_of_MOs=5;
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("MOs_Config.properties", false))); 
		   for(int i=0;i<No_of_MOs;i++)
		   {
		    out.println("Enter_MO_Type"+(i+1)+"=");
		    out.println("Ener_No_of_MOs_of_Type"+(i+1)+"=");
		    out.println("");
		   }
		    out.close();
		   	    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
