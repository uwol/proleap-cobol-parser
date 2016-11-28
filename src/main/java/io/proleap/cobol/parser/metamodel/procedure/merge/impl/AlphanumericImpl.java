/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.merge.impl;

import io.proleap.cobol.Cobol85Parser.MergeCollatingAlphanumericContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.merge.Alphanumeric;

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
