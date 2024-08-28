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

import java.io.File;

public class check_Delete_runtime_files {

	public void list_mml(String[] NE_Names, int l_max) {
		check_mml("script.sh");
		check_mml("del_script.sh");
		check_mml("ftpcon.sh");
		check_mml("mml_ports.mml");
		check_mml("mml_cre_ne.mml");
		check_mml("mml_del_sim.mml");
		check_mml("mml_assnports.mml");
		check_mml("mml_assndd.mml");
		check_mml("mml_start_ne.mml");
		check_mml("mml_MOs.mml");
		check_mml("mml_set_mos.mml");
		check_mml("mml_create_mos.mml");
		check_mml("mml_arne.mml");
		check_mml("mml_save_sim.mml");
		check_mml("mml_del_db_ne.mml");
		check_mml("mml_MOs.mo");

		for (int i = 0; i < NE_Names.length; i++) {
			check_mml(NE_Names[i] + ".mo");
			check_mml(NE_Names[i] + "_orig.mo");
			check_mml("Kertyle" + (i + 1) + ".mo");
			check_mml("set_mos" + (i + 1) + ".mo");
			check_mml("mml_set_name_mos_" + NE_Names[i] + ".mo");
			check_mml("mml_create_mos" + NE_Names[i] + ".mo");
			check_mml("mml_MOs_" + (i + 1) + ".mml");
			check_mml("script" + (i + 1) + ".sh");
			check_mml("kertyle_del_" + (i + 1) + ".mo");
			check_mml("mml_MOs_del_" + (i + 1) + ".mml");
			check_mml("set_mos" + (i + 1) + ".mo");
			for (int j = 0; j <= l_max; j++) {
				check_mml("mml_MOs_correct" + (i + 1) + "_" + j + ".mml");
				check_mml(NE_Names[i] + "_final" + j + ".mo");
				check_mml("Kertyle_final" + (i + 1) + "_" + j + ".mo");
			}
			for (int j = 0; j < 7; j++) {
				check_mml("Kertyle_aft_gen_" + j + ".mo");
				check_mml("Kertyle_parent_def_" + j + "_" + (i + 1) + ".mo");
			}

		}
	}

	public void check_mml(String File_Name) {
		File f = new File(File_Name);

		if (f.exists())
			f.delete();

	}
}
