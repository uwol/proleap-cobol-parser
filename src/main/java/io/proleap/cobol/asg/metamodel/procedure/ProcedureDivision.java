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
import io.proleap.cobol.CobolParser.ProcedureDeclarativesContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionGivingClauseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionUsingClauseContext;
import io.proleap.cobol.CobolParser.ProcedureSectionContext;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;

/**
 * Contains procedures to manipulate the data from data division.
 */
public interface ProcedureDivision extends Scope {

	Declaratives addDeclaratives(ProcedureDeclarativesContext ctx);

	GivingClause addGivingClause(ProcedureDivisionGivingClauseContext ctx);

	void addParagraph(Paragraph paragraph);

	Paragraph addParagraph(ParagraphContext ctx);

	ParagraphName addParagraphName(ParagraphNameContext ctx);

	Section addSection(ProcedureSectionContext ctx);

	UsingClause addUsingClause(ProcedureDivisionUsingClauseContext ctx);

	Declaratives getDeclaratives();

	GivingClause getGivingClause();

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

	/**
	 * Returns every @Section
	 */
	List<Section> getSections();

	/**
	 * Returns every @Section with the given name, including duplicate ones.
	 */
	List<Section> getSections(String name);

	UsingClause getUsingClause();
}
