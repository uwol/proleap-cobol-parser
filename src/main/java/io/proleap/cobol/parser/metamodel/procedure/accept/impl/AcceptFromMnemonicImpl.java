/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptFromMnemonicContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptFromMnemonic;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class AcceptFromMnemonicImpl extends StatementImpl implements AcceptFromMnemonic {

	protected final AcceptFromMnemonicContext ctx;

	protected ValueStmt mnemonicValueStmt;

	public AcceptFromMnemonicImpl(final ProgramUnit programUnit, final AcceptFromMnemonicContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getMnemonicValueStmt() {
		return mnemonicValueStmt;
	}

	@Override
	public void setMnemonicValueStmt(final ValueStmt mnemonicValueStmt) {
		this.mnemonicValueStmt = mnemonicValueStmt;
	}

}
