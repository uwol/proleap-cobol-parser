/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import io.proleap.cobol.CobolParser.PowersContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface MultDiv extends ValueStmt {

	enum MultDivType {
		DIV, MULT
	}

	Powers addPowers(PowersContext ctx);

	MultDivType getMultDivType();

	Powers getPowers();

	void setMultDivType(MultDivType multDivType);
}
