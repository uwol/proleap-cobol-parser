/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.open.impl;

import io.proleap.cobol.Cobol85Parser.OpenOutputContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.open.Output;

public class OutputImpl extends CobolDivisionElementImpl implements Output {

	protected final OpenOutputContext ctx;

	protected Call fileCall;

	protected boolean noRewind;

	public OutputImpl(final ProgramUnit programUnit, final OpenOutputContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public boolean isNoRewind() {
		return noRewind;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setNoRewind(final boolean noRewind) {
		this.noRewind = noRewind;
	}

}
