/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectForContext;
import io.proleap.cobol.Cobol85Parser.InspectTallyingPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.inspect.For;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Tallying;

public class TallyingImpl extends InspectPhraseImpl implements Tallying {

	protected final InspectTallyingPhraseContext ctx;

	protected For tallyFor;

	public TallyingImpl(final ProgramUnit programUnit, final InspectTallyingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public For addFor(final InspectForContext ctx) {
		For result = (For) getASGElement(ctx);

		if (result == null) {
			result = createFor(ctx);
			tallyFor = result;
		}

		return result;
	}

	@Override
	public For getFor() {
		return tallyFor;
	}

}
