/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import io.proleap.cobol.CobolParser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.programlibrary.GivingClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class GivingClauseImpl extends CobolDivisionElementImpl implements GivingClause {

	protected LibraryEntryProcedureGivingClauseContext ctx;

	protected Call givingCall;

	public GivingClauseImpl(final ProgramUnit programUnit, final LibraryEntryProcedureGivingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getGivingCall() {
		return givingCall;
	}

	@Override
	public void setGivingCall(final Call givingCall) {
		this.givingCall = givingCall;
	}

}
