/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface DestinationTableClause extends CobolDivisionElement {

	void addIndexCall(Call indexCall);

	List<Call> getIndexCalls();

	IntegerLiteral getOccursIntegerLiteral();

	void setIntegerLiteral(IntegerLiteral occursIntegerLiteral);
}
