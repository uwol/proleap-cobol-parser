/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SetToStatementContext;
import io.proleap.cobol.Cobol85Parser.SetUpDownByStatementContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Assigns a value to a COBOL reference.
 */
public interface SetStatement extends Statement {

	enum Type {
		By, To
	}

	SetBy addSetBy(SetUpDownByStatementContext ctx);

	SetTo addSetTo(SetToStatementContext ctx);

	SetBy getSetBy();

	List<SetTo> getSetTos();

	Type getType();

	void setType(Type type);

}
