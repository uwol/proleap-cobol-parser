/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.Cobol85Parser.UnstringIntoContext;
import io.proleap.cobol.Cobol85Parser.UnstringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringOrAllPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringStatementContext;
import io.proleap.cobol.Cobol85Parser.UnstringTallyingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Intos;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Tallying;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.WithPointer;

public class UnstringStatementImpl extends StatementImpl implements UnstringStatement {

	protected final UnstringStatementContext ctx;

	protected Intos intos;

	protected NotOnOverflowPhrase notOnOverflowPhrase;

	protected OnOverflowPhrase onOverflowPhrase;

	protected Sending sending;

	protected final StatementType statementType = StatementTypeEnum.UNSTRING;

	protected Tallying tallying;

	protected WithPointer withPointer;

	public UnstringStatementImpl(final ProgramUnit programUnit, final Scope scope, final UnstringStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Intos addIntos(final UnstringIntoPhraseContext ctx) {
		Intos result = (Intos) getASGElement(ctx);

		if (result == null) {
			result = new IntosImpl(programUnit, ctx);

			// intos
			for (final UnstringIntoContext unstringIntoContext : ctx.unstringInto()) {
				result.addInto(unstringIntoContext);
			}

			intos = result;
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
			final Call sendingCall = createCall(ctx.qualifiedDataName());
			result.setSendingCall(sendingCall);

			// delimited by
			if (ctx.unstringDelimitedByPhrase() != null) {
				result.addDelimitedBy(ctx.unstringDelimitedByPhrase());
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
	public Tallying addTallying(final UnstringTallyingPhraseContext ctx) {
		Tallying result = (Tallying) getASGElement(ctx);

		if (result == null) {
			result = new TallyingImpl(programUnit, ctx);

			final Call tallyCountDataItemCall = createCall(ctx.qualifiedDataName());
			result.addTallyCountDataItemCall(tallyCountDataItemCall);

			tallying = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WithPointer addWithPointer(final UnstringWithPointerPhraseContext ctx) {
		WithPointer result = (WithPointer) getASGElement(ctx);

		if (result == null) {
			result = new WithPointerImpl(programUnit, ctx);

			final Call pointerCall = createCall(ctx.qualifiedDataName());
			result.setPointerCall(pointerCall);

			withPointer = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Intos getIntos() {
		return intos;
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
	public Tallying getTallying() {
		return tallying;
	}

	@Override
	public WithPointer getWithPointer() {
		return withPointer;
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
