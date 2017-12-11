/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface MemorySizeClause extends CobolDivisionElement {

	enum Unit {
		CHARACTERS, MODULES, WORDS
	}

	Unit getUnit();

	ValueStmt getValueStmt();

	void setUnit(Unit unit);

	void setValueStmt(ValueStmt valueStmt);
}
