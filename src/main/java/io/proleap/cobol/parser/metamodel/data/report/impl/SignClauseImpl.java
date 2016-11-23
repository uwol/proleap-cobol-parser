/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupSignClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.SignClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SignClauseImpl extends CobolDivisionElementImpl implements SignClause {

	protected ReportGroupSignClauseContext ctx;

	protected boolean separate;

	protected Type type;

	public SignClauseImpl(final ProgramUnit programUnit, final ReportGroupSignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean isSeparate() {
		return separate;
	}

	@Override
	public void setSeparate(final boolean separate) {
		this.separate = separate;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
