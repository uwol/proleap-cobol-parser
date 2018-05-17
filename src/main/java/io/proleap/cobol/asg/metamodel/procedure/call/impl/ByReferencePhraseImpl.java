/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.CallByReferenceContext;
import io.proleap.cobol.CobolParser.CallByReferencePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReferencePhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByReferencePhraseImpl extends CobolDivisionElementImpl implements ByReferencePhrase {

	protected List<ByReference> byReferences = new ArrayList<ByReference>();

	protected final CallByReferencePhraseContext ctx;

	public ByReferencePhraseImpl(final ProgramUnit programUnit, final CallByReferencePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReference addByReference(final CallByReferenceContext ctx) {
		ByReference result = (ByReference) getASGElement(ctx);

		if (result == null) {
			result = new ByReferenceImpl(programUnit, ctx);

			// call
			final ValueStmt valueStmt = createValueStmt(ctx.fileName(), ctx.identifier(), ctx.literal());
			result.setValueStmt(valueStmt);

			// type
			final ByReference.ByReferenceType type;

			if (ctx.ADDRESS() != null) {
				type = ByReference.ByReferenceType.ADDRESS_OF;
			} else if (ctx.INTEGER() != null) {
				type = ByReference.ByReferenceType.INTEGER;
			} else if (ctx.STRING() != null) {
				type = ByReference.ByReferenceType.STRING;
			} else if (ctx.OMITTED() != null) {
				type = ByReference.ByReferenceType.OMITTED;
			} else {
				type = null;
			}

			result.setByReferenceType(type);

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
