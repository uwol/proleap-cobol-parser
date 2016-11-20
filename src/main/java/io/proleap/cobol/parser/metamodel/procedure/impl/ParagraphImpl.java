/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ParagraphName;

public class ParagraphImpl extends CobolDivisionElementImpl implements Paragraph {

	protected final List<ProcedureCall> calls = new ArrayList<ProcedureCall>();

	protected final ParagraphContext ctx;

	protected final String name;

	protected ParagraphName paragraphName;

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
	public String toString() {
		return "name=[" + name + "]";
	}

}
