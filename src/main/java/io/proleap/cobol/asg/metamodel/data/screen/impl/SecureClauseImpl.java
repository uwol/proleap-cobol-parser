/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionSecureClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.SecureClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SecureClauseImpl extends CobolDivisionElementImpl implements SecureClause {

	protected ScreenDescriptionSecureClauseContext ctx;

	protected SecureClauseType secureClauseType;

	public SecureClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionSecureClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public SecureClauseType getSecureClauseType() {
		return secureClauseType;
	}

	@Override
	public void setSecureClauseType(final SecureClauseType secureClauseType) {
		this.secureClauseType = secureClauseType;
	}

}
