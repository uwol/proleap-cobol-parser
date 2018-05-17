/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide;

import io.proleap.cobol.CobolParser.DivideByGivingStatementContext;
import io.proleap.cobol.CobolParser.DivideIntoGivingStatementContext;
import io.proleap.cobol.CobolParser.DivideIntoStatementContext;
import io.proleap.cobol.CobolParser.DivideRemainderContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Divides one number by another and store the result.
 */
public interface DivideStatement extends Statement {

	enum DivideType {
		BY_GIVING, INTO, INTO_GIVING
	}

	DivideByGivingStatement addDivideByGivingStatement(DivideByGivingStatementContext ctx);

	DivideIntoGivingStatement addDivideIntoGivingStatement(DivideIntoGivingStatementContext ctx);

	DivideIntoStatement addDivideIntoStatement(DivideIntoStatementContext ctx);

	Remainder addRemainder(DivideRemainderContext ctx);

	DivideByGivingStatement getDivideByGivingStatement();

	DivideIntoGivingStatement getDivideIntoGivingStatement();

	DivideIntoStatement getDivideIntoStatement();

	DivideType getDivideType();

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	ValueStmt getOperandValueStmt();

	Remainder getRemainder();

	void setDivideType(DivideType divideType);

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);

	void setOperandValueStmt(ValueStmt divisorValueStmt);
}
