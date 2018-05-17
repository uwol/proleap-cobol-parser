/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.StringDelimitedByPhraseContext;
import io.proleap.cobol.CobolParser.StringForPhraseContext;
import io.proleap.cobol.CobolParser.StringSendingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.ForPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SendingsImpl extends CobolDivisionElementImpl implements Sendings {

	protected final StringSendingPhraseContext ctx;

	protected DelimitedByPhrase delimitedByPhrase;

	protected ForPhrase sendingForPhrase;

	protected SendingsType sendingsType;

	protected List<ValueStmt> sendingValueStmts = new ArrayList<ValueStmt>();

	public SendingsImpl(final ProgramUnit programUnit, final StringSendingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DelimitedByPhrase addDelimitedByPhrase(final StringDelimitedByPhraseContext ctx) {
		DelimitedByPhrase result = (DelimitedByPhrase) getASGElement(ctx);

		if (result == null) {
			result = new DelimitedByPhraseImpl(programUnit, ctx);

			// type
			final DelimitedByPhrase.DelimitedByType type;

			if (ctx.SIZE() != null) {
				type = DelimitedByPhrase.DelimitedByType.SIZE;
			} else {
				final ValueStmt charactersValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
				result.setCharactersValueStmt(charactersValueStmt);
				type = DelimitedByPhrase.DelimitedByType.CHARACTERS;
			}

			result.setType(type);

			delimitedByPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ForPhrase addForPhrase(final StringForPhraseContext ctx) {
		ForPhrase result = (ForPhrase) getASGElement(ctx);

		if (result == null) {
			result = new ForPhraseImpl(programUnit, ctx);

			final ValueStmt forValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setForValueStmt(forValueStmt);

			sendingForPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addSendingValueStmt(final ValueStmt sendingValueStmt) {
		sendingValueStmts.add(sendingValueStmt);
	}

	@Override
	public DelimitedByPhrase getDelimitedByPhrase() {
		return delimitedByPhrase;
	}

	@Override
	public ForPhrase getForPhrase() {
		return sendingForPhrase;
	}

	@Override
	public SendingsType getSendingsType() {
		return sendingsType;
	}

	@Override
	public List<ValueStmt> getSendingValueStmts() {
		return sendingValueStmts;
	}

	@Override
	public void setSendingsType(final SendingsType sendingsType) {
		this.sendingsType = sendingsType;
	}

}
