/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply;

import io.proleap.cobol.CobolParser.MultiplyGivingContext;
import io.proleap.cobol.CobolParser.MultiplyRegularContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Multiplies numerics and stores the result
 */
public interface MultiplyStatement extends Statement {

	enum MultiplyType {
		BY, BY_GIVING
	}

	ByPhrase addByPhrase(MultiplyRegularContext ctx);

	GivingPhrase addGivingPhrase(MultiplyGivingContext ctx);

	ByPhrase getByPhrase();

	GivingPhrase getGivingPhrase();

	MultiplyType getMultiplyType();

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	ValueStmt getOperandValueStmt();

	void setMultiplyType(MultiplyType multiply);

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);

	void setOperandValueStmt(ValueStmt operandValueStmt);
}
