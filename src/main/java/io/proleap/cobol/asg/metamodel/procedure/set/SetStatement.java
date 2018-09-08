/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.CobolParser.SetToStatementContext;
import io.proleap.cobol.CobolParser.SetUpDownByStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Assigns a value to a COBOL reference.
 */
public interface SetStatement extends Statement {

	enum SetType {
		BY, TO
	}

	SetBy addSetBy(SetUpDownByStatementContext ctx);

	SetTo addSetTo(SetToStatementContext ctx);

	SetBy getSetBy();

	List<SetTo> getSetTos();

	SetType getSetType();

	void setSetType(SetType setType);
}
