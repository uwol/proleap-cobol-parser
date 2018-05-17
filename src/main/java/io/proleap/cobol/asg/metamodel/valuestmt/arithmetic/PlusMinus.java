/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import io.proleap.cobol.CobolParser.MultDivsContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface PlusMinus extends ValueStmt {

	enum PlusMinusType {
		MINUS, PLUS
	}

	MultDivs addMultDivs(MultDivsContext ctx);

	MultDivs getMultDivs();

	PlusMinusType getPlusMinusType();

	void setPlusMinusType(PlusMinusType plusMinusType);
}
