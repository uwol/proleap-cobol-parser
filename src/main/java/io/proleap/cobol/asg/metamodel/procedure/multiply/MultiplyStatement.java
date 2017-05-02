/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply;

import io.proleap.cobol.Cobol85Parser.MultiplyGivingContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Multiplies numerics and stores the result
 */
public interface MultiplyStatement extends Statement {

	enum Type {
		GIVING, REGULAR
	}

	Giving addGiving(MultiplyGivingContext ctx);

	Regular addRegular(MultiplyRegularContext ctx);

	Giving getGiving();

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	ValueStmt getOperandValueStmt();

	Regular getRegular();

	Type getType();

	void setNotOnSizeError(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);

	void setOperandValueStmt(ValueStmt operandValueStmt);

	void setType(Type type);

}
