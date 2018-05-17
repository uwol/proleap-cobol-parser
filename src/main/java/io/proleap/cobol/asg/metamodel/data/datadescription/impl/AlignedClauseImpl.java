/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataAlignedClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.AlignedClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class AlignedClauseImpl extends CobolDivisionElementImpl implements AlignedClause {

	protected boolean aligned;

	protected DataAlignedClauseContext ctx;

	public AlignedClauseImpl(final ProgramUnit programUnit, final DataAlignedClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isAligned() {
		return aligned;
	}

	@Override
	public void setAligned(final boolean aligned) {
		this.aligned = aligned;
	}

}
