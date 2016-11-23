/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryIsCommonClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.CommonClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class CommonClauseImpl extends CobolDivisionElementImpl implements CommonClause {

	protected boolean common;

	protected LibraryIsCommonClauseContext ctx;

	public CommonClauseImpl(final ProgramUnit programUnit, final LibraryIsCommonClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isCommon() {
		return common;
	}

	@Override
	public void setCommon(final boolean common) {
		this.common = common;
	}

}
