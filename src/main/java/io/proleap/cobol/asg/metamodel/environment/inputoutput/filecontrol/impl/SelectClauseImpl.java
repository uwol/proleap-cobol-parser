/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.SelectClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.SelectClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SelectClauseImpl extends CobolDivisionElementImpl implements SelectClause {

	protected final SelectClauseContext ctx;

	protected Call fileCall;

	protected final String name;

	protected boolean optional;

	public SelectClauseImpl(final String name, final ProgramUnit programUnit, final SelectClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public void addFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isOptional() {
		return optional;
	}

	@Override
	public void setOptional(final boolean optional) {
		this.optional = optional;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
