/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddTo;
import io.proleap.cobol.parser.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AddStatementImpl extends StatementImpl implements AddStatement {

	protected AddCorresponding addCorresponding;

	protected AddTo addTo;

	protected AddToGiving addToGiving;

	protected final AddStatementContext ctx;

	public AddStatementImpl(final ProgramUnit programUnit, final AddStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AddCorresponding addAddCorresponding(final AddCorrespondingStatementContext ctx) {
		AddCorresponding result = (AddCorresponding) getASGElement(ctx);

		if (result == null) {
			result = new AddCorrespondingImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddTo addAddTo(final AddToStatementContext ctx) {
		AddTo result = (AddTo) getASGElement(ctx);

		if (result == null) {
			result = new AddToImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddToGiving addAddToGiving(final AddToGivingStatementContext ctx) {
		AddToGiving result = (AddToGiving) getASGElement(ctx);

		if (result == null) {
			result = new AddToGivingImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddCorresponding getAddCorresponding() {
		return addCorresponding;
	}

	@Override
	public AddTo getAddTo() {
		return addTo;
	}

	@Override
	public AddToGiving getAddToGiving() {
		return addToGiving;
	}

}
