/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.OdtClauseContext;
import io.proleap.cobol.parser.metamodel.MnemonicName;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
