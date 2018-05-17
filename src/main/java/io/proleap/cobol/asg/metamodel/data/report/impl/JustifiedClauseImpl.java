/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupJustifiedClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.JustifiedClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class JustifiedClauseImpl extends CobolDivisionElementImpl implements JustifiedClause {

	protected ReportGroupJustifiedClauseContext ctx;

	protected Justified justified;

	public JustifiedClauseImpl(final ProgramUnit programUnit, final ReportGroupJustifiedClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Justified getJustified() {
		return justified;
	}

	@Override
	public void setJustified(final Justified justified) {
		this.justified = justified;
	}

}
