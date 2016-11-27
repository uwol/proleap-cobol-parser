/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.unstring.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.UnstringIntoContext;
import io.proleap.cobol.Cobol85Parser.UnstringIntoPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.unstring.Into;
import io.proleap.cobol.parser.metamodel.procedure.unstring.Intos;

public class IntosImpl extends CobolDivisionElementImpl implements Intos {

	protected final UnstringIntoPhraseContext ctx;

	protected List<Into> intos = new ArrayList<Into>();

	public IntosImpl(final ProgramUnit programUnit, final UnstringIntoPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Into addInto(final UnstringIntoContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			// call
			final Call intoCall = createCall(ctx.identifier());
			result.setIntoCall(intoCall);

			// delimiter in
			if (ctx.unstringDelimiterIn() != null) {
				result.addDelimiterIn(ctx.unstringDelimiterIn());
			}

			// count in
			if (ctx.unstringCountIn() != null) {
				result.addCountIn(ctx.unstringCountIn());
			}

			intos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Into> getIntos() {
		return intos;
	}

}
