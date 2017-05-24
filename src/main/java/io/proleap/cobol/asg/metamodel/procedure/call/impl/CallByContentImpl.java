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

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.Cobol85Parser.CallByContentPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByContent;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class CallByContentImpl extends CobolDivisionElementImpl implements CallByContent {

	protected List<ByContent> byContents = new ArrayList<ByContent>();

	protected final CallByContentPhraseContext ctx;

	public CallByContentImpl(final ProgramUnit programUnit, final CallByContentPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByContent addByContent(final CallByContentContext ctx) {
		ByContent result = (ByContent) getASGElement(ctx);

		if (result == null) {
			result = new ByContentImpl(programUnit, ctx);

			// call and type
			final ValueStmt valueStmt = createValueStmt(ctx.literal(), ctx.identifier());
			result.setValueStmt(valueStmt);

			// type
			final ByContent.ByContentType type;

			if (ctx.ADDRESS() != null) {
				type = ByContent.ByContentType.ADDRESS_OF;
			} else if (ctx.LENGTH() != null) {
				type = ByContent.ByContentType.LENGTH_OF;
			} else {
				type = null;
			}

			result.setByContentType(type);

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
