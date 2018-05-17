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

import io.proleap.cobol.CobolParser.StringIntoPhraseContext;
import io.proleap.cobol.CobolParser.StringSendingContext;
import io.proleap.cobol.CobolParser.StringSendingPhraseContext;
import io.proleap.cobol.CobolParser.StringStatementContext;
import io.proleap.cobol.CobolParser.StringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.IntoPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.WithPointerPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class StringStatementImpl extends StatementImpl implements StringStatement {

	protected final StringStatementContext ctx;

	protected IntoPhrase intoPhrase;

	protected NotOnOverflowPhrase notOnOverflowPhrase;

	protected OnOverflowPhrase onOverflowPhrase;

	protected List<Sendings> sendings = new ArrayList<Sendings>();

	protected final StatementType statementType = StatementTypeEnum.STRING;

	protected WithPointerPhrase withPointerPhrase;

	public StringStatementImpl(final ProgramUnit programUnit, final Scope scope, final StringStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntoPhrase addIntoPhrase(final StringIntoPhraseContext ctx) {
		IntoPhrase result = (IntoPhrase) getASGElement(ctx);

		if (result == null) {
			result = new IntoPhraseImpl(programUnit, ctx);

			// into
			if (ctx.identifier() != null) {
				final Call intoCall = createCall(ctx.identifier());
				result.setIntoCall(intoCall);
			}

			intoPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Sendings addSendings(final StringSendingPhraseContext ctx) {
		Sendings result = (Sendings) getASGElement(ctx);

		if (result == null) {
			result = new SendingsImpl(programUnit, ctx);

			// sending
			for (final StringSendingContext stringSendingContext : ctx.stringSending()) {
				final ValueStmt sendingValueStmt = createValueStmt(stringSendingContext.identifier(),
						stringSendingContext.literal());
				result.addSendingValueStmt(sendingValueStmt);
			}

			// type
			final Sendings.SendingsType type;

			if (ctx.stringDelimitedByPhrase() != null) {
				result.addDelimitedByPhrase(ctx.stringDelimitedByPhrase());
				type = Sendings.SendingsType.DELIMITED_BY;
			} else if (ctx.stringForPhrase() != null) {
				result.addForPhrase(ctx.stringForPhrase());
				type = Sendings.SendingsType.FOR;
			} else {
				type = null;
			}

			result.setSendingsType(type);

			sendings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WithPointerPhrase addWithPointerPhrase(final StringWithPointerPhraseContext ctx) {
		WithPointerPhrase result = (WithPointerPhrase) getASGElement(ctx);

		if (result == null) {
			result = new WithPointerPhraseImpl(programUnit, ctx);

			final Call pointerCall = createCall(ctx.qualifiedDataName());
			result.setPointerCall(pointerCall);

			withPointerPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IntoPhrase getIntoPhrase() {
		return intoPhrase;
	}

	@Override
	public NotOnOverflowPhrase getNotOnOverflowPhrase() {
		return notOnOverflowPhrase;
	}

	@Override
	public OnOverflowPhrase getOnOverflowPhrase() {
		return onOverflowPhrase;
	}

	@Override
	public List<Sendings> getSendings() {
		return sendings;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public WithPointerPhrase getWithPointerPhrase() {
		return withPointerPhrase;
	}

	@Override
	public void setNotOnOverflowPhrase(final NotOnOverflowPhrase notOnOverflowPhrase) {
		this.notOnOverflowPhrase = notOnOverflowPhrase;
	}

	@Override
	public void setOnOverflowPhrase(final OnOverflowPhrase onOverflowPhrase) {
		this.onOverflowPhrase = onOverflowPhrase;
	}

}
