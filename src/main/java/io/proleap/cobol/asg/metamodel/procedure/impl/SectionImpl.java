/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphName;
import io.proleap.cobol.asg.metamodel.procedure.Section;

public class SectionImpl extends ScopeImpl implements Section {

	protected final List<ProcedureCall> calls = new ArrayList<ProcedureCall>();

	protected final ProcedureSectionContext ctx;

	protected final String name;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, Paragraph> paragraphsSymbolTable = new HashMap<String, Paragraph>();

	public SectionImpl(final String name, final ProgramUnit programUnit, final ProcedureSectionContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addCall(final ProcedureCall procedureCall) {
		calls.add(procedureCall);
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, programUnit, ctx);

			paragraphs.add(result);
			paragraphsSymbolTable.put(name, result);

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
	public List<ProcedureCall> getCalls() {
		return calls;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Paragraph getParagraph(final String name) {
		return paragraphsSymbolTable.get(name);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
