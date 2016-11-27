/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.write.impl;

import io.proleap.cobol.Cobol85Parser.WriteAdvancingPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteAtEndOfPagePhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteFromPhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.Cobol85Parser.WriteStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.write.Advancing;
import io.proleap.cobol.parser.metamodel.procedure.write.AtEndOfPage;
import io.proleap.cobol.parser.metamodel.procedure.write.From;
import io.proleap.cobol.parser.metamodel.procedure.write.NotAtEndOfPage;
import io.proleap.cobol.parser.metamodel.procedure.write.WriteStatement;

public class WriteStatementImpl extends StatementImpl implements WriteStatement {

	protected Advancing advancing;

	protected AtEndOfPage atEndOfPage;

	protected final WriteStatementContext ctx;

	protected From from;

	protected InvalidKey invalidKey;

	protected NotAtEndOfPage notAtEndOfPage;

	protected NotInvalidKey notInvalidKey;

	protected Call recordCall;

	public WriteStatementImpl(final ProgramUnit programUnit, final WriteStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Advancing addAdvancing(final WriteAdvancingPhraseContext ctx) {
		Advancing result = (Advancing) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingImpl(programUnit, ctx);

			// type
			final Advancing.Type type;

			if (ctx.writeAdvancingPage() != null) {
				type = Advancing.Type.Page;
			} else if (ctx.writeAdvancingLines() != null) {
				result.addAdvancingLines(ctx.writeAdvancingLines());
				type = Advancing.Type.Lines;
			} else if (ctx.writeAdvancingMnemonic() != null) {
				result.addAdvancingMnemonic(ctx.writeAdvancingMnemonic());
				type = Advancing.Type.Mnemonic;
			} else {
				type = null;
			}

			result.setType(type);

			// position type
			final Advancing.PositionType positionType;

			if (ctx.AFTER() != null) {
				positionType = Advancing.PositionType.After;
			} else if (ctx.BEFORE() != null) {
				positionType = Advancing.PositionType.Before;
			} else {
				positionType = null;
			}

			result.setPositionType(positionType);

			advancing = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEndOfPage addAtEndOfPage(final WriteAtEndOfPagePhraseContext ctx) {
		AtEndOfPage result = (AtEndOfPage) getASGElement(ctx);

		if (result == null) {
			result = new AtEndOfPageImpl(programUnit, ctx);

			// FXIME statements

			atEndOfPage = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public From addFrom(final WriteFromPhraseContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			// from
			final Call fromCall = createCall(ctx.identifier(), ctx.literal());
			result.setFromCall(fromCall);

			from = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public NotAtEndOfPage addNotAtEndOfPage(final WriteNotAtEndOfPagePhraseContext ctx) {
		NotAtEndOfPage result = (NotAtEndOfPage) getASGElement(ctx);

		if (result == null) {
			result = new NotAtEndOfPageImpl(programUnit, ctx);

			// FXIME statements

			notAtEndOfPage = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Advancing getAdvancing() {
		return advancing;
	}

	@Override
	public AtEndOfPage getAtEndOfPage() {
		return atEndOfPage;
	}

	@Override
	public From getFrom() {
		return from;
	}

	@Override
	public InvalidKey getInvalidKey() {
		return invalidKey;
	}

	@Override
	public NotAtEndOfPage getNotAtEndOfPage() {
		return notAtEndOfPage;
	}

	@Override
	public NotInvalidKey getNotInvalidKey() {
		return notInvalidKey;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public void setInvalidKey(final InvalidKey invalidKey) {
		this.invalidKey = invalidKey;
	}

	@Override
	public void setNotInvalidKey(final NotInvalidKey notInvalidKey) {
		this.notInvalidKey = notInvalidKey;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}

}
