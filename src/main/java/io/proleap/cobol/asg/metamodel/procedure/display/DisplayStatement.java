/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display;

import java.util.List;

import io.proleap.cobol.CobolParser.DisplayAtContext;
import io.proleap.cobol.CobolParser.DisplayOperandContext;
import io.proleap.cobol.CobolParser.DisplayUponContext;
import io.proleap.cobol.CobolParser.DisplayWithContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Displays the value of a data item on a screen or writes it to a file.
 */
public interface DisplayStatement extends Statement {

	At addAt(DisplayAtContext ctx);

	Operand addOperand(DisplayOperandContext ctx);

	Upon addUpon(DisplayUponContext ctx);

	With addWith(DisplayWithContext ctx);

	At getAt();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	List<Operand> getOperands();

	Upon getUpon();

	With getWith();

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);
}
