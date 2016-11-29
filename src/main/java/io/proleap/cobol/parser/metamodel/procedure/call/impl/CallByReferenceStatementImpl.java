/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.call.ByReference;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByReferenceStatement;

public class CallByReferenceStatementImpl extends CobolDivisionElementImpl implements CallByReferenceStatement {

	protected List<ByReference> byReferences = new ArrayList<ByReference>();

	protected final CallByReferenceStatementContext ctx;

	public CallByReferenceStatementImpl(final ProgramUnit programUnit, final CallByReferenceStatementContext ctx) {
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
				type = ByReference.Type.AddressOf;
			} else if (ctx.INTEGER() != null) {
				type = ByReference.Type.Integer;
			} else if (ctx.STRING() != null) {
				type = ByReference.Type.String;
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
