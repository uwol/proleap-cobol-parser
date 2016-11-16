/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface RecordContainsClause extends CobolDivisionElement {

	ValueStmt getDependingOnValueStmt();

	IntegerLiteral getFrom();

	IntegerLiteral getTo();

	Boolean isVarying();

	void setDependingOnValueStmt(ValueStmt dependingOnValueStmt);

	void setFrom(IntegerLiteral from);

	void setTo(IntegerLiteral to);

	void setVarying(Boolean varying);

}
