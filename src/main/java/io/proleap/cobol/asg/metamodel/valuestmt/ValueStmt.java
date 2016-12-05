/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ValueStmt extends CobolDivisionElement {

	void addSubValueStmt(ValueStmt subValueStmt);

	List<ValueStmt> getSubValueStmts();

	Object getValue();
}
