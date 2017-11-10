/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDeclarativesContext;
import io.proleap.cobol.Cobol85Parser.ProcedureSectionContext;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;

/**
 * Contains procedures to manipulate the data from data division.
 */
public interface ProcedureDivision extends Scope {

	Declaratives addDeclaratives(ProcedureDeclarativesContext ctx);

	void addParagraph(Paragraph paragraph);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	Section addSection(ProcedureSectionContext ctx);

	Declaratives getDeclaratives();

	/**
	 * Returns the first @Paragraph with the given name, including ones nested in
	 * sections.
	 */
	Paragraph getParagraph(String name);

	/**
	 * Returns every @Paragraph, including ones nested in sections.
	 */
	List<Paragraph> getParagraphs();

	/**
	 * Returns every @Paragraph with the given name, including ones with the given
	 * name nested in sections.
	 */
	List<Paragraph> getParagraphs(String name);

	/**
	 * Returns every @Paragraph, excluding ones nested in sections.
	 */
	List<Paragraph> getRootParagraphs();

	/**
	 * Returns the first @Section with the given name.
	 */
	Section getSection(String name);

	List<Section> getSections();

	/**
	 * Returns every @Section with the given name, including duplicate ones.
	 */
	List<Section> getSections(String name);
}
