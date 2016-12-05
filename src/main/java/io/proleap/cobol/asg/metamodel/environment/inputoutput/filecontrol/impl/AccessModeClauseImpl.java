/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.AccessModeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AccessModeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class AccessModeClauseImpl extends CobolDivisionElementImpl implements AccessModeClause {

	protected final AccessModeClauseContext ctx;

	protected Mode mode;

	public AccessModeClauseImpl(final ProgramUnit programUnit, final AccessModeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Mode getMode() {
		return mode;
	}

	@Override
	public void setMode(final Mode mode) {
		this.mode = mode;
	}

}
