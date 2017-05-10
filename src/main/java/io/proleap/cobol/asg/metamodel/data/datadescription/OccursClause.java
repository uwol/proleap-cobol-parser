/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataOccursSortContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OccursClause extends CobolDivisionElement {

	Index addIndex(IndexNameContext ctx);

	OccursSort addOccursSort(DataOccursSortContext ctx);

	Call getDependingOnCall();

	IntegerLiteral getFrom();

	Index getIndex(String name);

	List<Index> getIndices();

	List<OccursSort> getOccursSorts();

	IntegerLiteral getTo();

	void setDependingOnCall(Call dependingOnCall);

	void setFrom(IntegerLiteral from);

	void setTo(IntegerLiteral to);
}
