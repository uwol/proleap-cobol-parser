/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptFromDateContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptFromDate;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AcceptFromDateImpl extends StatementImpl implements AcceptFromDate {

	protected final AcceptFromDateContext ctx;

	protected DateType dateType;

	public AcceptFromDateImpl(final ProgramUnit programUnit, final AcceptFromDateContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DateType getDateType() {
		return dateType;
	}

	@Override
	public void setDateType(final DateType dateType) {
		this.dateType = dateType;
	}

}
