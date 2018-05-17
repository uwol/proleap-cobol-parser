/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add;

import io.proleap.cobol.CobolParser.AddCorrespondingStatementContext;
import io.proleap.cobol.CobolParser.AddToGivingStatementContext;
import io.proleap.cobol.CobolParser.AddToStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Adds two or more numeric operands and stores the result.
 */
public interface AddStatement extends Statement {

	enum AddType {
		CORRESPONDING, TO_GIVING, TO
	}

	AddCorrespondingStatement addAddCorrespondingStatement(AddCorrespondingStatementContext ctx);

	AddToGivingStatement addAddToGivingStatement(AddToGivingStatementContext ctx);

	AddToStatement addAddToStatement(AddToStatementContext ctx);

	AddCorrespondingStatement getAddCorrespondingStatement();

	AddToGivingStatement getAddToGivingStatement();

	AddToStatement getAddToStatement();

	AddType getAddType();

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	void setAddType(AddType addType);

	void setNotOnSizePhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);
}
