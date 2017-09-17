/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import java.util.List;

import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;

public interface Paragraph extends Scope, Declaration {

	void addCall(ProcedureCall procedureCall);

	void addParagraphName(ParagraphName paragraphName);

	List<ProcedureCall> getCalls();

	ParagraphName getParagraphName();

	Section getSection();

	void setSection(Section section);
}
