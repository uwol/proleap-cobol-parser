/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface MultDiv extends ValueStmt {

	enum Type {
		DIV, MULT
	}

	Powers addPowers(PowersContext ctx);

	Powers getPowers();

	Type getType();

	@Override
	String getValue();

	void setType(Type type);

}
