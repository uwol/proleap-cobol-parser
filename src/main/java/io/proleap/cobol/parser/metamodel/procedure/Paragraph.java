/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure;

import java.util.List;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.Declaration;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;

public interface Paragraph extends CobolDivisionElement, Declaration {

	void addParagraphName(ParagraphName paragraphName);

	void addProcedureCall(ProcedureCall procedureCall);

	ParagraphName getParagraphName();

	List<ProcedureCall> getProcedureCalls();
}
