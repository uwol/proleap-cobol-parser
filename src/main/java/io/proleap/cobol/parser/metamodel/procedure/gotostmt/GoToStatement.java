/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.gotostmt;

import io.proleap.cobol.Cobol85Parser.GoToDependingOnStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementSimpleContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Transfers control to one or more statements. Thus similar to a PERFORM,
 * except that after execution control stays there.
 */
public interface GoToStatement extends Statement {

	enum Type {
		DependingOn, Simple
	}

	DependingOn addDependingOn(GoToDependingOnStatementContext ctx);

	Simple addSimple(GoToStatementSimpleContext ctx);

	DependingOn getDependingOn();

	Simple getSimple();

	Type getType();

	void setType(Type type);
}
