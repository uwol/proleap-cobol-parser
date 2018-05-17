/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.ChannelClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.MnemonicName;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ChannelClauseImpl extends CobolDivisionElementImpl implements ChannelClause {

	protected final ChannelClauseContext ctx;

	protected IntegerLiteral integerLiteral;

	protected MnemonicName mnemonicName;

	public ChannelClauseImpl(final ProgramUnit programUnit, final ChannelClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public MnemonicName getMnemonicName() {
		return mnemonicName;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public void setMnemonicName(final MnemonicName mnemonicName) {
		this.mnemonicName = mnemonicName;
	}

}
