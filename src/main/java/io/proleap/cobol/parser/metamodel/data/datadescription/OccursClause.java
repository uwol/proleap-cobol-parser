/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataOccursSortContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface OccursClause extends CobolDivisionElement {

	ValueStmt addIndexNameValueStmt(IndexNameContext ctx);

	OccursSort addOccursSort(DataOccursSortContext ctx);

	ValueStmt getDependingOnValueStmt();

	IntegerLiteral getFrom();

	List<ValueStmt> getIndexNameValueStmts();

	List<OccursSort> getOccursSorts();

	IntegerLiteral getTo();

	void setDependingOnValueStmt(ValueStmt dependingOnValueStmt);

	void setFrom(IntegerLiteral from);

	void setTo(IntegerLiteral to);
}
