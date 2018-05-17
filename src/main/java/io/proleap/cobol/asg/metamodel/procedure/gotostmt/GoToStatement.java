/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt;

import io.proleap.cobol.CobolParser.GoToDependingOnStatementContext;
import io.proleap.cobol.CobolParser.GoToStatementSimpleContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers control to one or more statements. Thus similar to a PERFORM,
 * except that after execution control stays there.
 */
public interface GoToStatement extends Statement {

	enum GoToType {
		DEPENDING_ON, SIMPLE
	}

	DependingOnPhrase addDependingOnPhrase(GoToDependingOnStatementContext ctx);

	Simple addSimple(GoToStatementSimpleContext ctx);

	DependingOnPhrase getDependingOnPhrase();

	GoToType getGoToType();

	Simple getSimple();

	void setGoToType(GoToType goToType);
}
