/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Adds two or more numeric operands and stores the result.
 */
public interface AddStatement extends Statement {

	enum Type {
		Corresponding, Giving, To
	}

	AddCorresponding addAddCorresponding(AddCorrespondingStatementContext ctx);

	AddTo addAddTo(AddToStatementContext ctx);

	AddToGiving addAddToGiving(AddToGivingStatementContext ctx);

	AddCorresponding getAddCorresponding();

	AddTo getAddTo();

	AddToGiving getAddToGiving();

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	Type getType();

	void setNotOnSize(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);

	void setType(Type type);
}
