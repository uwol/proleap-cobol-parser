/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.divide;

import io.proleap.cobol.Cobol85Parser.DivideIntoByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Divides one number by another and store the result.
 */
public interface DivideStatement extends Statement {

	enum Type {
		Into, IntoByGiving, IntoGiving
	}

	Into addInto(DivideIntoStatementContext ctx);

	IntoByGiving addIntoByGiving(DivideIntoByGivingStatementContext ctx);

	IntoGiving addIntoGiving(DivideIntoGivingStatementContext ctx);

	Remainder addRemainder(DivideRemainderContext ctx);

	Call getDivisorCall();

	Into getInto();

	IntoByGiving getIntoByGiving();

	IntoGiving getIntoGiving();

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	Remainder getRemainder();

	Type getType();

	void setDivisorCall(Call divisorCall);

	void setNotOnSizeError(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);

	void setType(Type type);

}
