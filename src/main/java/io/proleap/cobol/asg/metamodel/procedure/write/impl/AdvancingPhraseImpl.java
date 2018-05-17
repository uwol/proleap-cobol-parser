/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write.impl;

import io.proleap.cobol.CobolParser.WriteAdvancingLinesContext;
import io.proleap.cobol.CobolParser.WriteAdvancingMnemonicContext;
import io.proleap.cobol.CobolParser.WriteAdvancingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingLines;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingMnemonic;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AdvancingPhraseImpl extends CobolDivisionElementImpl implements AdvancingPhrase {

	protected AdvancingLines advancingLines;

	protected AdvancingMnemonic advancingMnemonic;

	protected AdvancingType advancingType;

	protected WriteAdvancingPhraseContext ctx;

	protected PositionType positionType;

	public AdvancingPhraseImpl(final ProgramUnit programUnit, final WriteAdvancingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AdvancingLines addAdvancingLines(final WriteAdvancingLinesContext ctx) {
		AdvancingLines result = (AdvancingLines) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingLinesImpl(programUnit, ctx);

			final ValueStmt linesValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setLinesValueStmt(linesValueStmt);

			advancingLines = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AdvancingMnemonic addAdvancingMnemonic(final WriteAdvancingMnemonicContext ctx) {
		AdvancingMnemonic result = (AdvancingMnemonic) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingMnemonicImpl(programUnit, ctx);

			final Call mnemonicCall = createCall(ctx.mnemonicName());
			result.setMnemonicCall(mnemonicCall);

			advancingMnemonic = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AdvancingLines getAdvancingLines() {
		return advancingLines;
	}

	@Override
	public AdvancingMnemonic getAdvancingMnemonic() {
		return advancingMnemonic;
	}

	@Override
	public AdvancingType getAdvancingType() {
		return advancingType;
	}

	@Override
	public PositionType getPositionType() {
		return positionType;
	}

	@Override
	public void setAdvancingType(final AdvancingType advancingType) {
		this.advancingType = advancingType;
	}

	@Override
	public void setPositionType(final PositionType positionType) {
		this.positionType = positionType;
	}

}
