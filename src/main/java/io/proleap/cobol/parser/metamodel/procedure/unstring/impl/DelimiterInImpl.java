/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringDelimiterInContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.unstring.DelimiterIn;

public class DelimiterInImpl extends CobolDivisionElementImpl implements DelimiterIn {

	protected final UnstringDelimiterInContext ctx;

	protected Call delimiterInCall;

	public DelimiterInImpl(final ProgramUnit programUnit, final UnstringDelimiterInContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDelimiterInCall() {
		return delimiterInCall;
	}

	@Override
	public void setDelimiterInCall(final Call delimiterInCall) {
		this.delimiterInCall = delimiterInCall;
	}

}
