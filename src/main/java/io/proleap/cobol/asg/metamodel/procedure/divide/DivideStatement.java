/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide;

import io.proleap.cobol.Cobol85Parser.DivideByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Divides one number by another and store the result.
 */
public interface DivideStatement extends Statement {

	enum Type {
		BY_GIVING, INTO, INTO_GIVING
	}

	ByGiving addByGiving(DivideByGivingStatementContext ctx);

	Into addInto(DivideIntoStatementContext ctx);

	IntoGiving addIntoGiving(DivideIntoGivingStatementContext ctx);

	Remainder addRemainder(DivideRemainderContext ctx);

	ByGiving getByGiving();

	ValueStmt getDivisorValueStmt();

	Into getInto();

	IntoGiving getIntoGiving();

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	Remainder getRemainder();

	Type getType();

	void setDivisorValueStmt(ValueStmt divisorValueStmt);

	void setNotOnSizeError(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);

	void setType(Type type);

}
