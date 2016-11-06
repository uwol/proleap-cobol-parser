/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Paragraph;
import io.proleap.cobol.parser.metamodel.ParagraphName;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;

public class ParagraphImpl extends CobolScopedElementImpl implements Paragraph {

	protected final ParagraphContext ctx;

	protected final String name;

	protected ParagraphName paragraphName;

	protected final List<ProcedureCall> procedureCalls = new ArrayList<ProcedureCall>();

	public ParagraphImpl(final String name, final CopyBook copyBook, final CobolScope superScope,
			final ParagraphContext ctx) {
		super(copyBook, superScope, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addParagraphName(final ParagraphName paragraphName) {
		this.paragraphName = paragraphName;
	}

	@Override
	public void addProcedureCall(final ProcedureCall procedureCall) {
		procedureCalls.add(procedureCall);
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
	public List<ProcedureCall> getProcedureCalls() {
		return procedureCalls;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
