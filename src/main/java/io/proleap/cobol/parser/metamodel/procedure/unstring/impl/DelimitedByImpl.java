/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringDelimitedByPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.unstring.DelimitedBy;

public class DelimitedByImpl extends CobolDivisionElementImpl implements DelimitedBy {

	protected final UnstringDelimitedByPhraseContext ctx;

	protected Call delimitedByCall;

	public DelimitedByImpl(final ProgramUnit programUnit, final UnstringDelimitedByPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDelimitedByCall() {
		return delimitedByCall;
	}

	@Override
	public void setDelimitedByCall(final Call delimitedByCall) {
		this.delimitedByCall = delimitedByCall;
	}

}
