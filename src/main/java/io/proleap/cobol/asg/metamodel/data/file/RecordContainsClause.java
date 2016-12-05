/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface RecordContainsClause extends CobolDivisionElement {

	Call getDependingOnCall();

	IntegerLiteral getFrom();

	IntegerLiteral getTo();

	boolean isVarying();

	void setDependingOnCall(Call dependingOnCall);

	void setFrom(IntegerLiteral from);

	void setTo(IntegerLiteral to);

	void setVarying(boolean varying);

}
