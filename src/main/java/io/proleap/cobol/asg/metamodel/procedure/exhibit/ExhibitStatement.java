/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exhibit;

import java.util.List;

import io.proleap.cobol.CobolParser.ExhibitOperandContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Displays the value of a data item on a screen.
 */
public interface ExhibitStatement extends Statement {

	Operand addOperand(ExhibitOperandContext ctx);

	List<Operand> getOperands();

	boolean isChanged();

	boolean isNamed();

	void setChanged(boolean changed);

	void setNamed(boolean named);
}
