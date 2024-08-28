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

import java.io.File;

public class AbsolutePath {

	public String getAbsolutePath(String name) {
		String path;
		File directory = new File(name);
		boolean isDirectory = directory.isDirectory();
		if (isDirectory) {
			path = directory.getAbsolutePath();
		} else {
			path = directory.getAbsolutePath();
		}

		return path;

	}
}
