/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import io.proleap.cobol.CobolParser.WriteAdvancingPhraseContext;
import io.proleap.cobol.CobolParser.WriteFromPhraseContext;
import io.proleap.cobol.CobolParser.WriteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.AtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.From;
import io.proleap.cobol.asg.metamodel.procedure.write.NotAtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class WriteStatementImpl extends StatementImpl implements WriteStatement {

	protected AdvancingPhrase advancingPhrase;

	protected AtEndOfPagePhrase atEndOfPagePhrase;

	protected final WriteStatementContext ctx;

	protected From from;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected NotAtEndOfPagePhrase notAtEndOfPagePhrase;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected Call recordCall;

	protected final StatementType statementType = StatementTypeEnum.WRITE;

	public WriteStatementImpl(final ProgramUnit programUnit, final Scope scope, final WriteStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AdvancingPhrase addAdvancingPhrase(final WriteAdvancingPhraseContext ctx) {
		AdvancingPhrase result = (AdvancingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingPhraseImpl(programUnit, ctx);

			// type
			final AdvancingPhrase.AdvancingType type;

			if (ctx.writeAdvancingPage() != null) {
				type = AdvancingPhrase.AdvancingType.PAGE;
			} else if (ctx.writeAdvancingLines() != null) {
				result.addAdvancingLines(ctx.writeAdvancingLines());
				type = AdvancingPhrase.AdvancingType.LINES;
			} else if (ctx.writeAdvancingMnemonic() != null) {
				result.addAdvancingMnemonic(ctx.writeAdvancingMnemonic());
				type = AdvancingPhrase.AdvancingType.MNEMONIC;
			} else {
				type = null;
			}

			result.setAdvancingType(type);

			// position type
			final AdvancingPhrase.PositionType positionType;

			if (ctx.AFTER() != null) {
				positionType = AdvancingPhrase.PositionType.AFTER;
			} else if (ctx.BEFORE() != null) {
				positionType = AdvancingPhrase.PositionType.BEFORE;
			} else {
				positionType = null;
			}

			result.setPositionType(positionType);

			advancingPhrase = result;
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
			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setFromValueStmt(fromValueStmt);

			from = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AdvancingPhrase getAdvancingPhrase() {
		return advancingPhrase;
	}

	@Override
	public AtEndOfPagePhrase getAtEndOfPagePhrase() {
		return atEndOfPagePhrase;
	}

	@Override
	public From getFrom() {
		return from;
	}

	@Override
	public InvalidKeyPhrase getInvalidKeyPhrase() {
		return invalidKeyPhrase;
	}

	@Override
	public NotAtEndOfPagePhrase getNotAtEndOfPagePhrase() {
		return notAtEndOfPagePhrase;
	}

	@Override
	public NotInvalidKeyPhrase getNotInvalidKeyPhrase() {
		return notInvalidKeyPhrase;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setAtEndOfPagePhrase(final AtEndOfPagePhrase atEndOfPagePhrase) {
		this.atEndOfPagePhrase = atEndOfPagePhrase;
	}

	@Override
	public void setInvalidKeyPhrase(final InvalidKeyPhrase invalidKeyPhrase) {
		this.invalidKeyPhrase = invalidKeyPhrase;
	}

	@Override
	public void setNotAtEndOfPagePhrase(final NotAtEndOfPagePhrase notAtEndOfPagePhrase) {
		this.notAtEndOfPagePhrase = notAtEndOfPagePhrase;
	}

	@Override
	public void setNotInvalidKeyPhrase(final NotInvalidKeyPhrase notInvalidKeyPhrase) {
		this.notInvalidKeyPhrase = notInvalidKeyPhrase;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}
}
