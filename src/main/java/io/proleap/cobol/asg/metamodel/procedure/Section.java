/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import java.util.List;

import io.proleap.cobol.CobolParser.ParagraphContext;
import io.proleap.cobol.CobolParser.ParagraphNameContext;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.SectionCall;

public interface Section extends Scope, Declaration {

	void addCall(SectionCall sectionCall);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	List<SectionCall> getCalls();

	Paragraph getParagraph(String name);

	List<Paragraph> getParagraphs();

	List<Paragraph> getParagraphs(String name);
}
