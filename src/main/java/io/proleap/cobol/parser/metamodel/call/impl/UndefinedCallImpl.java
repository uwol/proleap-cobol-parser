/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;

public class UndefinedCallImpl extends CallImpl implements Call {

	public UndefinedCallImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final ParseTree ctx) {
		super(name, programUnit, scope, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.UndefinedCall;
	}
}
