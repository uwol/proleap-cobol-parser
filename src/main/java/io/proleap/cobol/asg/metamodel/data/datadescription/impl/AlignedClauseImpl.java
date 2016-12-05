/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataAlignedClauseContext;
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
