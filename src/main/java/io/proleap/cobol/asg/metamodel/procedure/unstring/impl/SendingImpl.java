/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.UnstringDelimitedByPhraseContext;
import io.proleap.cobol.CobolParser.UnstringOrAllPhraseContext;
import io.proleap.cobol.CobolParser.UnstringSendingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.OrAll;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SendingImpl extends CobolDivisionElementImpl implements Sending {

	protected final UnstringSendingPhraseContext ctx;

	protected DelimitedByPhrase delimitedByPhrase;

	protected List<OrAll> orAlls = new ArrayList<OrAll>();

	protected Call sendingCall;

	public SendingImpl(final ProgramUnit programUnit, final UnstringSendingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DelimitedByPhrase addDelimitedByPhrase(final UnstringDelimitedByPhraseContext ctx) {
		DelimitedByPhrase result = (DelimitedByPhrase) getASGElement(ctx);

		if (result == null) {
			result = new DelimitedByPhraseImpl(programUnit, ctx);

			final ValueStmt delimitedByValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setDelimitedByValueStmt(delimitedByValueStmt);

			delimitedByPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OrAll addOrAll(final UnstringOrAllPhraseContext ctx) {
		OrAll result = (OrAll) getASGElement(ctx);

		if (result == null) {
			result = new OrAllImpl(programUnit, ctx);

			final ValueStmt orAllValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setOrAllValueStmt(orAllValueStmt);

			orAlls.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DelimitedByPhrase getDelimitedByPhrase() {
		return delimitedByPhrase;
	}

	@Override
	public List<OrAll> getOrAlls() {
		return orAlls;
	}

	@Override
	public Call getSendingCall() {
		return sendingCall;
	}

	@Override
	public void setSendingCall(final Call sendingCall) {
		this.sendingCall = sendingCall;
	}

}
