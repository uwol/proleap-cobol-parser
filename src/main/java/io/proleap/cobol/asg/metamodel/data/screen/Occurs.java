/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;

public interface Occurs extends CobolDivisionElement {

	IntegerLiteral getOccursTimes();

	void setOccursTimes(IntegerLiteral occursTimes);

}
