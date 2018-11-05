/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.CobolParser.DataOccursDependingContext;
import io.proleap.cobol.CobolParser.DataOccursIndexedContext;
import io.proleap.cobol.CobolParser.DataOccursSortContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface OccursClause extends CobolDivisionElement {

	OccursDepending addOccursDepending(DataOccursDependingContext ctx);

	OccursIndexed addOccursIndexed(DataOccursIndexedContext ctx);

	OccursSort addOccursSort(DataOccursSortContext ctx);

	ValueStmt getFrom();

	OccursDepending getOccursDepending();

	OccursIndexed getOccursIndexed();

	List<OccursSort> getOccursSorts();

	IntegerLiteral getTo();

	void setFrom(ValueStmt from);

	void setTo(IntegerLiteral to);
}
