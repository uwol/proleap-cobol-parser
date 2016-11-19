/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportDescriptionGlobalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.GlobalClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class GlobalClauseImpl extends CobolDivisionElementImpl implements GlobalClause {

	protected final ReportDescriptionGlobalClauseContext ctx;

	protected Boolean global;

	public GlobalClauseImpl(final ProgramUnit programUnit, final ReportDescriptionGlobalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Boolean isGlobal() {
		return global;
	}

	@Override
	public void setGlobal(final Boolean global) {
		this.global = global;
	}

}
