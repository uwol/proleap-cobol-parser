/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface DiskSizeClause extends CobolDivisionElement {

	enum Unit {
		MODULES, WORDS
	}

	Unit getUnit();

	ValueStmt getValueStmt();

	void setUnit(Unit unit);

	void setValueStmt(ValueStmt valueStmt);
}
