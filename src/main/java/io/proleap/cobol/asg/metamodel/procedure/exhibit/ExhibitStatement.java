/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exhibit;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ExhibitOperandContext;
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
