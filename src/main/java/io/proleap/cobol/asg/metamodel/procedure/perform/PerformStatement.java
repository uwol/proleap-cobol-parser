/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.CobolParser.PerformInlineStatementContext;
import io.proleap.cobol.CobolParser.PerformProcedureStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers control to one or more statements. After execution returns control
 * to the next executable statement. Thus similar to a GOTO, except that after
 * execution control returns back to the next line after the perform statement.
 */
public interface PerformStatement extends Statement {

	enum PerformStatementType {
		INLINE, PROCEDURE
	}

	PerformInlineStatement addPerformInlineStatement(PerformInlineStatementContext ctx);

	PerformProcedureStatement addPerformProcedureStatement(PerformProcedureStatementContext ctx);

	PerformInlineStatement getPerformInlineStatement();

	PerformProcedureStatement getPerformProcedureStatement();

	PerformStatementType getPerformStatementType();

	void setPerformStatementType(PerformStatementType performStatementType);
}
