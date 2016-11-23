/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.Cobol85Parser.ExternalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.ExternalClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ExternalClauseImpl extends CobolDivisionElementImpl implements ExternalClause {

	protected ExternalClauseContext ctx;

	protected boolean external;

	public ExternalClauseImpl(final ProgramUnit programUnit, final ExternalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isExternal() {
		return external;
	}

	@Override
	public void setExternal(final boolean external) {
		this.external = external;
	}
}
