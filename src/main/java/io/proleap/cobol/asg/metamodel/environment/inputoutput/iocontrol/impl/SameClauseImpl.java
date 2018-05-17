/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SameClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SameClauseImpl extends CobolDivisionElementImpl implements SameClause {

	protected final SameClauseContext ctx;

	protected List<Call> fileCalls = new ArrayList<Call>();

	protected Form form;

	public SameClauseImpl(final ProgramUnit programUnit, final SameClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileCall(final Call fileCall) {
		fileCalls.add(fileCall);
	}

	@Override
	public List<Call> getFileCalls() {
		return fileCalls;
	}

	@Override
	public Form getForm() {
		return form;
	}

	@Override
	public void setForm(final Form form) {
		this.form = form;
	}

}
