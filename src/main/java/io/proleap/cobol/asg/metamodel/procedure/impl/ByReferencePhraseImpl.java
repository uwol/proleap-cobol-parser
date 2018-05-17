/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ProcedureDivisionByReferenceContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionByReferencePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.ByReferencePhrase;

public class ByReferencePhraseImpl extends CobolDivisionElementImpl implements ByReferencePhrase {

	protected List<ByReference> byReferences = new ArrayList<ByReference>();

	protected final ProcedureDivisionByReferencePhraseContext ctx;

	public ByReferencePhraseImpl(final ProgramUnit programUnit, final ProcedureDivisionByReferencePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReference addByReference(final ProcedureDivisionByReferenceContext ctx) {
		ByReference result = (ByReference) getASGElement(ctx);

		if (result == null) {
			result = new ByReferenceImpl(programUnit, ctx);

			// optional
			if (ctx.OPTIONAL() != null) {
				result.setOptional(true);
			}

			// any
			if (ctx.ANY() != null) {
				result.setAny(true);
			}

			// call
			final Call referenceCall = createCall(ctx.identifier(), ctx.fileName());
			result.setReferenceCall(referenceCall);

			byReferences.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ByReference> getByReferences() {
		return byReferences;
	}
}
