/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.StringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringSendingContext;
import io.proleap.cobol.Cobol85Parser.StringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringStatementContext;
import io.proleap.cobol.Cobol85Parser.StringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.Into;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.WithPointer;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class StringStatementImpl extends StatementImpl implements StringStatement {

	protected final StringStatementContext ctx;

	protected Into into;

	protected NotOnOverflowPhrase notOnOverflowPhrase;

	protected OnOverflowPhrase onOverflowPhrase;

	protected List<Sendings> sendings = new ArrayList<Sendings>();

	protected final StatementType statementType = StatementTypeEnum.STRING;

	protected WithPointer withPointer;

	public StringStatementImpl(final ProgramUnit programUnit, final Scope scope, final StringStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Into addInto(final StringIntoPhraseContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			// into
			final Call intoCall = createCall(ctx.identifier());
			result.setIntoCall(intoCall);

			into = result;
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
				final ValueStmt sendingValueStmt = createValueStmt(stringSendingContext.tableCall(),
						stringSendingContext.literal());
				result.addSendingValueStmt(sendingValueStmt);
			}

			// type
			final Sendings.SendingsType type;

			if (ctx.stringDelimitedByPhrase() != null) {
				result.addDelimitedBy(ctx.stringDelimitedByPhrase());
				type = Sendings.SendingsType.DELIMITED_BY;
			} else if (ctx.stringForPhrase() != null) {
				result.addFor(ctx.stringForPhrase());
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
	public WithPointer addWithPointer(final StringWithPointerPhraseContext ctx) {
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
	public Into getInto() {
		return into;
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
