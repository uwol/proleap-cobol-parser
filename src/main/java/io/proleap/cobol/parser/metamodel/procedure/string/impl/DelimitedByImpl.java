/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.string.impl;

import io.proleap.cobol.Cobol85Parser.StringDelimitedByPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.string.DelimitedBy;

public class DelimitedByImpl extends CobolDivisionElementImpl implements DelimitedBy {

	protected Call charactersCall;

	protected final StringDelimitedByPhraseContext ctx;

	protected Type type;

	public DelimitedByImpl(final ProgramUnit programUnit, final StringDelimitedByPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCharactersCall() {
		return charactersCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setCharactersCall(final Call charactersCall) {
		this.charactersCall = charactersCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
