/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.CobolParser.ClassClauseThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ClassClause extends CobolDivisionElement {

	enum ClassClauseType {
		ALPHA_NUMERIC, NATIONAL
	}

	ClassThrough addClassThrough(ClassClauseThroughContext ctx);

	Call getClassCall();

	ClassClauseType getClassClauseType();

	List<ClassThrough> getClassThroughs();

	void setClassCall(Call classCall);

	void setClassClauseType(ClassClauseType classClauseType);
}
