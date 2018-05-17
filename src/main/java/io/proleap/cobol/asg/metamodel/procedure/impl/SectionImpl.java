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

import io.proleap.cobol.CobolParser.ParagraphContext;
import io.proleap.cobol.CobolParser.ParagraphNameContext;
import io.proleap.cobol.CobolParser.ProcedureSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.SectionCall;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphName;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphsSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.procedure.Section;

public class SectionImpl extends ScopeImpl implements Section {

	protected final List<SectionCall> calls = new ArrayList<SectionCall>();

	protected final ProcedureSectionContext ctx;

	protected final String name;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, ParagraphsSymbolTableEntry> paragraphsSymbolTable = new HashMap<String, ParagraphsSymbolTableEntry>();

	public SectionImpl(final String name, final ProgramUnit programUnit, final ProcedureSectionContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addCall(final SectionCall sectionCall) {
		calls.add(sectionCall);
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, programUnit, ctx);

			paragraphs.add(result);
			result.setSection(this);

			assureParagraphsSymbolTableEntry(name).addParagraph(result);
			programUnit.getProcedureDivision().addParagraph(result);

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

	protected ParagraphsSymbolTableEntry assureParagraphsSymbolTableEntry(final String name) {
		ParagraphsSymbolTableEntry paragraphsSymbolTableEntry = paragraphsSymbolTable.get(getSymbol(name));

		if (paragraphsSymbolTableEntry == null) {
			paragraphsSymbolTableEntry = new ParagraphsSymbolTableEntryImpl();
			paragraphsSymbolTable.put(getSymbol(name), paragraphsSymbolTableEntry);
		}

		return paragraphsSymbolTableEntry;
	}

	@Override
	public List<SectionCall> getCalls() {
		return calls;
	}

	@Override
	public String getName() {
		return name;
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
	public String toString() {
		return "name=[" + name + "]";
	}
}
