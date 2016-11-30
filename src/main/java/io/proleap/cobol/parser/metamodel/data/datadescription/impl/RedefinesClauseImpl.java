/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataRedefinesClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.datadescription.RedefinesClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class RedefinesClauseImpl extends CobolDivisionElementImpl implements RedefinesClause {

	protected DataRedefinesClauseContext ctx;

	protected Call redefinesCall;

	public RedefinesClauseImpl(final ProgramUnit programUnit, final DataRedefinesClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getRedefinesCall() {
		return redefinesCall;
	}

	@Override
	public void setRedefinesCall(final Call redefinesCall) {
		this.redefinesCall = redefinesCall;
	}

}
