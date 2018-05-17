/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.LiteralContext;
import io.proleap.cobol.CobolParser.ParagraphContext;
import io.proleap.cobol.CobolParser.ParagraphNameContext;
import io.proleap.cobol.CobolParser.ProcedureDeclarativeContext;
import io.proleap.cobol.CobolParser.ProcedureDeclarativesContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionGivingClauseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionUsingClauseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionUsingParameterContext;
import io.proleap.cobol.CobolParser.ProcedureSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.GivingClause;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphName;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphsSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.SectionsSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.procedure.UsingClause;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.impl.DeclarativesImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.LiteralValueStmtImpl;

public class ProcedureDivisionImpl extends ScopeImpl implements ProcedureDivision {

	protected final ProcedureDivisionContext ctx;

	protected Declaratives declaratives;

	protected GivingClause givingClause;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, ParagraphsSymbolTableEntry> paragraphsSymbolTable = new HashMap<String, ParagraphsSymbolTableEntry>();

	protected List<Section> sections = new ArrayList<Section>();

	protected Map<String, SectionsSymbolTableEntry> sectionsSymbolTable = new HashMap<String, SectionsSymbolTableEntry>();

	protected UsingClause usingClause;

	public ProcedureDivisionImpl(final ProgramUnit programUnit, final ProcedureDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Declaratives addDeclaratives(final ProcedureDeclarativesContext ctx) {
		Declaratives result = (Declaratives) getASGElement(ctx);

		if (result == null) {
			result = new DeclarativesImpl(programUnit, ctx);
			declaratives = result;

			// declaratives
			for (final ProcedureDeclarativeContext procedureDeclarativeContext : ctx.procedureDeclarative()) {
				result.addDeclarative(procedureDeclarativeContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingClause addGivingClause(final ProcedureDivisionGivingClauseContext ctx) {
		GivingClause result = (GivingClause) getASGElement(ctx);

		if (result == null) {
			result = new GivingClauseImpl(programUnit, ctx);

			final Call givingCall = createCall(ctx.dataName());
			result.setGivingCall(givingCall);

			givingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addParagraph(final Paragraph paragraph) {
		final String name = paragraph.getName();

		paragraphs.add(paragraph);
		assureParagraphsSymbolTableEntry(name).addParagraph(paragraph);
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, programUnit, ctx);

			addParagraph(result);

			final ParagraphName paragraphName = addParagraphName(ctx.paragraphName());
			result.addParagraphName(paragraphName);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ParagraphName addParagraphName(final ParagraphNameContext ctx) {
		ParagraphName result = (ParagraphName) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphNameImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Section addSection(final ProcedureSectionContext ctx) {
		Section result = (Section) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SectionImpl(name, programUnit, ctx);

			sections.add(result);
			assureSectionsSymbolTableEntry(name).addSection(result);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingClause addUsingClause(final ProcedureDivisionUsingClauseContext ctx) {
		UsingClause result = (UsingClause) getASGElement(ctx);

		if (result == null) {
			result = new UsingClauseImpl(programUnit, ctx);

			// parameters
			for (final ProcedureDivisionUsingParameterContext procedureDivisionUsingParameterContext : ctx
					.procedureDivisionUsingParameter()) {
				result.addUsingParameter(procedureDivisionUsingParameterContext);
			}

			usingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	public ValueStmt addValueStmt(final LiteralContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new LiteralValueStmtImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected ParagraphsSymbolTableEntry assureParagraphsSymbolTableEntry(final String name) {
		ParagraphsSymbolTableEntry paragraphsSymbolTableEntry = paragraphsSymbolTable.get(getSymbol(name));

		if (paragraphsSymbolTableEntry == null) {
			paragraphsSymbolTableEntry = new ParagraphsSymbolTableEntryImpl();
			paragraphsSymbolTable.put(getSymbol(name), paragraphsSymbolTableEntry);
		}

		return paragraphsSymbolTableEntry;
	}

	protected SectionsSymbolTableEntry assureSectionsSymbolTableEntry(final String name) {
		SectionsSymbolTableEntry sectionsSymbolTableEntry = sectionsSymbolTable.get(getSymbol(name));

		if (sectionsSymbolTableEntry == null) {
			sectionsSymbolTableEntry = new SectionsSymbolTableEntryImpl();
			sectionsSymbolTable.put(getSymbol(name), sectionsSymbolTableEntry);
		}

		return sectionsSymbolTableEntry;
	}

	@Override
	public Declaratives getDeclaratives() {
		return declaratives;
	}

	@Override
	public GivingClause getGivingClause() {
		return givingClause;
	}

	@Override
	public Paragraph getParagraph(final String name) {
		return paragraphsSymbolTable.get(getSymbol(name)) == null ? null
				: paragraphsSymbolTable.get(getSymbol(name)).getParagraph();
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	@Override
	public List<Paragraph> getParagraphs(final String name) {
		return paragraphsSymbolTable.get(getSymbol(name)).getParagraphs();
	}

	@Override
	public List<Paragraph> getRootParagraphs() {
		final List<Paragraph> result = new ArrayList<Paragraph>();

		for (final Paragraph paragraph : paragraphs) {
			if (paragraph.getSection() == null) {
				result.add(paragraph);
			}
		}

		return result;
	}

	@Override
	public Section getSection(final String name) {
		return sectionsSymbolTable.get(getSymbol(name)) == null ? null
				: sectionsSymbolTable.get(getSymbol(name)).getSection();
	}

	@Override
	public List<Section> getSections() {
		return sections;
	}

	@Override
	public List<Section> getSections(final String name) {
		return sectionsSymbolTable.get(getSymbol(name)).getSections();
	}

	@Override
	public UsingClause getUsingClause() {
		return usingClause;
	}
}
