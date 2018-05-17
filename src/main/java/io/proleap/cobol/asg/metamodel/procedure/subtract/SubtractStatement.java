/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract;

import io.proleap.cobol.CobolParser.SubtractCorrespondingStatementContext;
import io.proleap.cobol.CobolParser.SubtractFromGivingStatementContext;
import io.proleap.cobol.CobolParser.SubtractFromStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Subtracts one or the sum of multiple data items from one or more data items.
 */
public interface SubtractStatement extends Statement {

	enum SubtractType {
		CORRESPONDING, FROM, FROM_GIVING
	}

	SubtractCorrespondingStatement addSubtractCorrespondingStatement(SubtractCorrespondingStatementContext ctx);

	SubtractFromGivingStatement addSubtractFromGivingStatement(SubtractFromGivingStatementContext ctx);

	SubtractFromStatement addSubtractFromStatement(SubtractFromStatementContext ctx);

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	SubtractCorrespondingStatement getSubtractCorrespondingStatement();

	SubtractFromGivingStatement getSubtractFromGivingStatement();

	SubtractFromStatement getSubtractFromStatement();

	SubtractType getSubtractType();

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);

	void setSubtractType(SubtractType subtractType);
}
