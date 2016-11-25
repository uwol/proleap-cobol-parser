/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

public interface Then extends CobolDivisionElement {

	Statement addStatement(StatementContext ctx);

	List<Statement> getStatements();

	boolean isNextSentence();

	void setNextSentence(boolean nextSentence);
}
