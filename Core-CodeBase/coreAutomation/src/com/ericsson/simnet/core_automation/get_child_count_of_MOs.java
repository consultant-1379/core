package com.ericsson.simnet.core_automation;

import java.util.ArrayList;

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

public class get_child_count_of_MOs {

	public ArrayList<Integer> get_count_MOs(ArrayList<String> MO_Child,
			ArrayList<String> MO_Parent) {
		// ArrayList<String> MO_Child = new ArrayList<String>();
		// ArrayList<String> MO_Parent = new ArrayList<String>();
		ArrayList<Integer> MOs_count = new ArrayList<Integer>();
		// MO_Child.add("b");MO_Child.add("c");MO_Child.add("m");MO_Child.add("n");MO_Child.add("x");MO_Child.add("o");MO_Child.add("p");MO_Child.add("e");MO_Child.add("f");MO_Child.add("q");MO_Child.add("r");MO_Child.add("s");MO_Child.add("t");MO_Child.add("w");MO_Child.add("u");MO_Child.add("v");MO_Child.add("h");MO_Child.add("i");MO_Child.add("z");MO_Child.add("k");MO_Child.add("l");MO_Child.add("y");MO_Child.add("aa");MO_Child.add("bb");
		// MO_Parent.add("a");MO_Parent.add("a");
		// MO_Parent.add("b");MO_Parent.add("b");MO_Parent.add("n");MO_Parent.add("c");
		// MO_Parent.add("c"); MO_Parent.add("d"); MO_Parent.add("d");
		// MO_Parent.add("e"); MO_Parent.add("e"); MO_Parent.add("f");
		// MO_Parent.add("f"); MO_Parent.add("r");
		// MO_Parent.add("t");MO_Parent.add("t"); MO_Parent.add("g");
		// MO_Parent.add("g"); MO_Parent.add("i"); MO_Parent.add("j");
		// MO_Parent.add("j");
		// MO_Parent.add("l");MO_Parent.add("v");MO_Parent.add("v");

		for (int i = 0; i < MO_Child.size(); i++) {
			int Count = 1;
			for (int j = 0; j < MO_Parent.size(); j++) {
				if (MO_Child.get(i).equals(MO_Parent.get(j))) {
					Count = Count + 1;
					for (int k = 0; k < MO_Parent.size(); k++) {
						if (MO_Child.get(j).equals(MO_Parent.get(k))) {
							Count = Count + 1;
							for (int l = 0; l < MO_Parent.size(); l++) {
								if (MO_Child.get(k).equals(MO_Parent.get(l))) {
									Count = Count + 1;
									for (int m = 0; m < MO_Parent.size(); m++) {
										if (MO_Child.get(l).equals(
												MO_Parent.get(m))) {
											Count = Count + 1;
											for (int n = 0; n < MO_Parent
													.size(); n++) {
												if (MO_Child.get(m).equals(
														MO_Parent.get(n))) {
													Count = Count + 1;
													for (int o = 0; o < MO_Parent
															.size(); o++) {
														if (MO_Child
																.get(n)
																.equals(MO_Parent
																		.get(o))) {
															Count = Count + 1;
															for (int p = 0; p < MO_Parent
																	.size(); p++) {
																if (MO_Child
																		.get(o)
																		.equals(MO_Parent
																				.get(p))) {
																	Count = Count + 1;
																	for (int q = 0; q < MO_Parent
																			.size(); q++) {
																		if (MO_Child
																				.get(p)
																				.equals(MO_Parent
																						.get(q))) {
																			Count = Count + 1;
																			for (int r = 0; r < MO_Parent
																					.size(); r++) {
																				if (MO_Child
																						.get(q)
																						.equals(MO_Parent
																								.get(r))) {
																					Count = Count + 1;

																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			/*
			 * if (Count>1) { Count=Count-1; }
			 */
			MOs_count.add(Count);
			// System.out.println("The size of MOs_Count in Test_MOs is "+MOs_count.size());
			// System.out.println(MO_Child.get(i)+" "+Count);
		}
		return MOs_count;
	}
}
