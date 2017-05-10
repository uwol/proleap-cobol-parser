/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.Cobol85Parser.CallByReferencePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByReference;

public class CallByReferenceImpl extends CobolDivisionElementImpl implements CallByReference {

	protected List<ByReference> byReferences = new ArrayList<ByReference>();

	protected final CallByReferencePhraseContext ctx;

	public CallByReferenceImpl(final ProgramUnit programUnit, final CallByReferencePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReference addByReference(final CallByReferenceContext ctx) {
		ByReference result = (ByReference) getASGElement(ctx);

		if (result == null) {
			result = new ByReferenceImpl(programUnit, ctx);

			// call and type
			final Call call = createCall(ctx.fileName(), ctx.identifier());
			result.setCall(call);

			// type
			final ByReference.Type type;

			if (ctx.ADDRESS() != null) {
				type = ByReference.Type.ADDRESS_OF;
			} else if (ctx.INTEGER() != null) {
				type = ByReference.Type.INTEGER;
			} else if (ctx.STRING() != null) {
				type = ByReference.Type.STRING;
			} else {
				type = null;
			}

			result.setType(type);

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
