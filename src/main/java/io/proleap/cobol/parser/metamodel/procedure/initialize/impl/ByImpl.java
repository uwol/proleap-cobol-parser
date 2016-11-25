/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.initialize.impl;

import io.proleap.cobol.Cobol85Parser.InitializeReplacingByContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.initialize.By;

public class ByImpl extends CobolDivisionElementImpl implements By {

	protected final InitializeReplacingByContext ctx;

	protected Type type;

	protected Call valueCall;

	public ByImpl(final ProgramUnit programUnit, final InitializeReplacingByContext ctx) {
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
