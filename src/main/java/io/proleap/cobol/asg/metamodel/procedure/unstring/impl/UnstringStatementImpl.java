/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringIntoContext;
import io.proleap.cobol.CobolParser.UnstringIntoPhraseContext;
import io.proleap.cobol.CobolParser.UnstringOrAllPhraseContext;
import io.proleap.cobol.CobolParser.UnstringSendingPhraseContext;
import io.proleap.cobol.CobolParser.UnstringStatementContext;
import io.proleap.cobol.CobolParser.UnstringTallyingPhraseContext;
import io.proleap.cobol.CobolParser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.IntoPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.procedure.unstring.TallyingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.WithPointerPhrase;

public class UnstringStatementImpl extends StatementImpl implements UnstringStatement {

	protected final UnstringStatementContext ctx;

	protected IntoPhrase intoPhrase;

	protected NotOnOverflowPhrase notOnOverflowPhrase;

	protected OnOverflowPhrase onOverflowPhrase;

	protected Sending sending;

	protected final StatementType statementType = StatementTypeEnum.UNSTRING;

	protected TallyingPhrase tallyingPhrase;

	protected WithPointerPhrase withPointerPhrase;

	public UnstringStatementImpl(final ProgramUnit programUnit, final Scope scope, final UnstringStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntoPhrase addIntoPhrase(final UnstringIntoPhraseContext ctx) {
		IntoPhrase result = (IntoPhrase) getASGElement(ctx);

		if (result == null) {
			result = new IntoPhraseImpl(programUnit, ctx);

			// intos
			for (final UnstringIntoContext unstringIntoContext : ctx.unstringInto()) {
				result.addInto(unstringIntoContext);
			}

			intoPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Sending addSending(final UnstringSendingPhraseContext ctx) {
		Sending result = (Sending) getASGElement(ctx);

		if (result == null) {
			result = new SendingImpl(programUnit, ctx);

			// sending call
			final Call sendingCall = createCall(ctx.identifier());
			result.setSendingCall(sendingCall);

			// delimited by
			if (ctx.unstringDelimitedByPhrase() != null) {
				result.addDelimitedByPhrase(ctx.unstringDelimitedByPhrase());
			}

			// or all
			for (final UnstringOrAllPhraseContext unstringOrAllPhraseContext : ctx.unstringOrAllPhrase()) {
				result.addOrAll(unstringOrAllPhraseContext);
			}

			sending = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TallyingPhrase addTallyingPhrase(final UnstringTallyingPhraseContext ctx) {
		TallyingPhrase result = (TallyingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new TallyingPhraseImpl(programUnit, ctx);

			final Call tallyCountDataItemCall = createCall(ctx.qualifiedDataName());
			result.addTallyCountDataItemCall(tallyCountDataItemCall);

			tallyingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WithPointerPhrase addWithPointerPhrase(final UnstringWithPointerPhraseContext ctx) {
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
	public Sending getSending() {
		return sending;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public TallyingPhrase getTallyingPhrase() {
		return tallyingPhrase;
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
