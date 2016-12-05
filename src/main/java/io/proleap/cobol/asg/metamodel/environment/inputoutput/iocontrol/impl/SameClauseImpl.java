/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SameClauseContext;
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
