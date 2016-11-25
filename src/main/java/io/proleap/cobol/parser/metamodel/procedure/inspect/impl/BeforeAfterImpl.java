/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.inspect.BeforeAfter;

public class BeforeAfterImpl extends InspectPhraseImpl implements BeforeAfter {

	protected final InspectBeforeAfterContext ctx;

	protected Call dataItemCall;

	protected Type type;

	public BeforeAfterImpl(final ProgramUnit programUnit, final InspectBeforeAfterContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataItemCall() {
		return dataItemCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDataItemCall(final Call dataItemCall) {
		this.dataItemCall = dataItemCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
