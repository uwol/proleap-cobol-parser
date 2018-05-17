/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import io.proleap.cobol.CobolParser.MergeCollatingAlphanumericContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.Alphanumeric;

public class AlphanumericImpl extends CobolDivisionElementImpl implements Alphanumeric {

	protected Call alphabetCall;

	protected final MergeCollatingAlphanumericContext ctx;

	public AlphanumericImpl(final ProgramUnit programUnit, final MergeCollatingAlphanumericContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getAlphabetCall() {
		return alphabetCall;
	}

	@Override
	public void setAlphabetCall(final Call alphabetCall) {
		this.alphabetCall = alphabetCall;
	}

}
