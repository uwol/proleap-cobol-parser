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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.Cobol85Parser.CallByContentStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.call.ByContent;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByContentStatement;

public class CallByContentStatementImpl extends CobolDivisionElementImpl implements CallByContentStatement {

	private final static Logger LOG = LogManager.getLogger(CallByContentStatementImpl.class);

	protected List<ByContent> byContents = new ArrayList<ByContent>();

	protected final CallByContentStatementContext ctx;

	public CallByContentStatementImpl(final ProgramUnit programUnit, final CallByContentStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByContent addByContent(final CallByContentContext ctx) {
		ByContent result = (ByContent) getASGElement(ctx);

		if (result == null) {
			result = new ByContentImpl(programUnit, ctx);

			// call and type
			final Call call = createCall(ctx.literal(), ctx.identifier());
			result.setCall(call);

			// type
			final ByContent.Type type;

			if (ctx.ADDRESS() != null) {
				type = ByContent.Type.AddressOf;
			} else if (ctx.LENGTH() != null) {
				type = ByContent.Type.LengthOf;
			} else {
				type = null;
			}

			result.setType(type);

			byContents.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ByContent> getByContents() {
		return byContents;
	}

}
