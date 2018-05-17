/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphName;
import io.proleap.cobol.asg.metamodel.procedure.Section;

public class ParagraphImpl extends ScopeImpl implements Paragraph {

	protected final List<ProcedureCall> calls = new ArrayList<ProcedureCall>();

	protected final ParagraphContext ctx;

	protected final String name;

	protected ParagraphName paragraphName;

	protected Section section;

	public ParagraphImpl(final String name, final ProgramUnit programUnit, final ParagraphContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addCall(final ProcedureCall procedureCall) {
		calls.add(procedureCall);
	}

	@Override
	public void addParagraphName(final ParagraphName paragraphName) {
		this.paragraphName = paragraphName;
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
	public ParagraphName getParagraphName() {
		return paragraphName;
	}

	@Override
	public Section getSection() {
		return section;
	}

	@Override
	public void setSection(final Section section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
