/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.OdtClauseContext;
import io.proleap.cobol.asg.metamodel.MnemonicName;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class OdtClauseImpl extends CobolDivisionElementImpl implements OdtClause {

	protected final OdtClauseContext ctx;

	protected MnemonicName mnemonicName;

	public OdtClauseImpl(final ProgramUnit programUnit, final OdtClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MnemonicName getMnemonicName() {
		return mnemonicName;
	}

	@Override
	public void setMnemonicName(final MnemonicName mnemonicName) {
		this.mnemonicName = mnemonicName;
	}

}
