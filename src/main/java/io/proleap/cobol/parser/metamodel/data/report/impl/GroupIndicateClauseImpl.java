/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupIndicateClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.GroupIndicateClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class GroupIndicateClauseImpl extends CobolDivisionElementImpl implements GroupIndicateClause {

	protected ReportGroupIndicateClauseContext ctx;

	protected boolean indicate;

	public GroupIndicateClauseImpl(final ProgramUnit programUnit, final ReportGroupIndicateClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isIndicate() {
		return indicate;
	}

	@Override
	public void setIndicate(final boolean indicate) {
		this.indicate = indicate;
	}

}
