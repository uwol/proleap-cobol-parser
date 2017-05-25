/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddCorrespondingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.To;

public class AddCorrespondingStatementImpl extends CobolDivisionElementImpl implements AddCorrespondingStatement {

	protected final AddCorrespondingStatementContext ctx;

	protected Call from;

	protected To to;

	public AddCorrespondingStatementImpl(final ProgramUnit programUnit, final AddCorrespondingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFrom() {
		return from;
	}

	@Override
	public To getTo() {
		return to;
	}

	@Override
	public void setFrom(final Call from) {
		this.from = from;
	}

	@Override
	public void setTo(final To to) {
		this.to = to;
	}

}
