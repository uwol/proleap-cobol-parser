/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.string.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.StringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringSendingContext;
import io.proleap.cobol.Cobol85Parser.StringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringStatementContext;
import io.proleap.cobol.Cobol85Parser.StringWithPointerPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.string.Into;
import io.proleap.cobol.parser.metamodel.procedure.string.Sendings;
import io.proleap.cobol.parser.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.parser.metamodel.procedure.string.WithPointer;

public class StringStatementImpl extends StatementImpl implements StringStatement {

	protected final StringStatementContext ctx;

	protected Into into;

	protected NotOnOverflow notOnOverflow;

	protected OnOverflow onOverflow;

	protected List<Sendings> sendings = new ArrayList<Sendings>();

	protected WithPointer withPointer;

	public StringStatementImpl(final ProgramUnit programUnit, final StringStatementContext ctx) {
		super(programUnit, ctx);

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

			// sending calls
			for (final StringSendingContext stringSendingContext : ctx.stringSending()) {
				final Call sendingCall = createCall(stringSendingContext.tableCall(), stringSendingContext.literal());
				result.addSendingCall(sendingCall);
			}

			// type
			final Sendings.Type type;

			if (ctx.stringDelimitedByPhrase() != null) {
				result.addDelimitedBy(ctx.stringDelimitedByPhrase());
				type = Sendings.Type.DelimitedBy;
			} else if (ctx.stringForPhrase() != null) {
				result.addFor(ctx.stringForPhrase());
				type = Sendings.Type.For;
			} else {
				type = null;
			}

			result.setType(type);

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
	public NotOnOverflow getNotOnOverflow() {
		return notOnOverflow;
	}

	@Override
	public OnOverflow getOnOverflow() {
		return onOverflow;
	}

	@Override
	public List<Sendings> getSendings() {
		return sendings;
	}

	@Override
	public WithPointer getWithPointer() {
		return withPointer;
	}

	@Override
	public void setNotOnOverflow(final NotOnOverflow notOnOverflow) {
		this.notOnOverflow = notOnOverflow;
	}

	@Override
	public void setOnOverflow(final OnOverflow onOverflow) {
		this.onOverflow = onOverflow;
	}

}
