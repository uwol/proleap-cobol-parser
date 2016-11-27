/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract;

import io.proleap.cobol.Cobol85Parser.SubtractCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromStatementContext;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Subtracts one or the sum of multiple data items from one or more data items.
 */
public interface SubtractStatement extends Statement {

	enum Type {
		Corresponding, From, FromGiving
	}

	SubtractCorresponding addSubtractCorresponding(SubtractCorrespondingStatementContext ctx);

	SubtractFrom addSubtractFrom(SubtractFromStatementContext ctx);

	SubtractFromGiving addSubtractFromGiving(SubtractFromGivingStatementContext ctx);

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	SubtractCorresponding getSubtractCorresponding();

	SubtractFrom getSubtractFrom();

	SubtractFromGiving getSubtractFromGiving();

	Type getType();

	void setNotOnSizeError(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);

	void setType(Type type);
}
