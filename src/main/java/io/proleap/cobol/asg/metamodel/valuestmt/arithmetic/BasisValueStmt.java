/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface BasisValueStmt extends ValueStmt {

	ValueStmt getBasisValueStmt();

	@Override
	String getValue();

	void setBasisValueStmt(ValueStmt basisValueStmt);

}
