/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;

public interface BlockContainsClause extends CobolDivisionElement {

	enum Unit {
		CHARACTERS, RECORDS
	}

	IntegerLiteral getFrom();

	IntegerLiteral getTo();

	Unit getUnit();

	void setFrom(IntegerLiteral from);

	void setTo(IntegerLiteral to);

	void setUnit(Unit unit);
}
