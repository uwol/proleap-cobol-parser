/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.set.impl;

import io.proleap.cobol.Cobol85Parser.SetToValueContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.set.Value;

public class ValueImpl extends CobolDivisionElementImpl implements Value {

	protected SetToValueContext ctx;

	protected Type type;

	protected Call valueCall;

	public ValueImpl(final ProgramUnit programUnit, final SetToValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Call getValueCall() {
		return valueCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

	@Override
	public void setValueCall(final Call valueCall) {
		this.valueCall = valueCall;
	}

}
