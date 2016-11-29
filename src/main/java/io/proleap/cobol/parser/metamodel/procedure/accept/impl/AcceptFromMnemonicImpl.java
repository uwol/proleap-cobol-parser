/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptFromMnemonic;

public class AcceptFromMnemonicImpl extends CobolDivisionElementImpl implements AcceptFromMnemonic {

	protected final AcceptFromMnemonicStatementContext ctx;

	protected Call mnemonicCall;

	public AcceptFromMnemonicImpl(final ProgramUnit programUnit, final AcceptFromMnemonicStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getMnemonicCall() {
		return mnemonicCall;
	}

	@Override
	public void setMnemonicCall(final Call mnemonicCall) {
		this.mnemonicCall = mnemonicCall;
	}

}
