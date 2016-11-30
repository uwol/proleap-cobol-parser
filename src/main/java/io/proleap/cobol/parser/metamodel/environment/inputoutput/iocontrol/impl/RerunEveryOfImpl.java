/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.RerunEveryOfContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.RerunEveryOf;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class RerunEveryOfImpl extends CobolDivisionElementImpl implements RerunEveryOf {

	protected final RerunEveryOfContext ctx;

	protected Call fileCall;

	protected Type type;

	public RerunEveryOfImpl(final ProgramUnit programUnit, final RerunEveryOfContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}
}
