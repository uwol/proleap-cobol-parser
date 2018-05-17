/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SortOnKeyClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.OnKey;

public class OnKeyImpl extends CobolDivisionElementImpl implements OnKey {

	protected final SortOnKeyClauseContext ctx;

	protected List<Call> keyCalls = new ArrayList<Call>();

	protected OnKeyType onKeyType;

	public OnKeyImpl(final ProgramUnit programUnit, final SortOnKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addKeyCall(final Call keyCall) {
		keyCalls.add(keyCall);
	}

	@Override
	public List<Call> getKeyCalls() {
		return keyCalls;
	}

	@Override
	public OnKeyType getOnKeyType() {
		return onKeyType;
	}

	@Override
	public void setOnKeyType(final OnKeyType onKeyType) {
		this.onKeyType = onKeyType;
	}

}
