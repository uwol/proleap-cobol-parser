/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DisplayAtContext;
import io.proleap.cobol.Cobol85Parser.DisplayOperandContext;
import io.proleap.cobol.Cobol85Parser.DisplayUponContext;
import io.proleap.cobol.Cobol85Parser.DisplayWithContext;
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

	List<Operand> getOperands();

	Upon getUpon();

	With getWith();

}
