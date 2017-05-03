/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import io.proleap.cobol.Cobol85Parser.MultDivsContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface PlusMinus extends ValueStmt {

	enum Type {
		MINUS, PLUS
	}

	MultDivs addMultDivs(MultDivsContext ctx);

	MultDivs getMultDivs();

	Type getType();

	void setType(Type type);

}
