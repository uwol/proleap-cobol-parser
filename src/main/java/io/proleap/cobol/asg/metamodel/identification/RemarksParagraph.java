/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

/**
 * Describes the function of the program.
 */
public interface RemarksParagraph extends CobolDivisionElement {

	String getRemarks();

	void setRemarks(String remarks);

}
