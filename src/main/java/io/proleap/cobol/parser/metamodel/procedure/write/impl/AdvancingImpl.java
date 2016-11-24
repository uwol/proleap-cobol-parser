/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.write.impl;

import io.proleap.cobol.Cobol85Parser.WriteAdvancingLinesContext;
import io.proleap.cobol.Cobol85Parser.WriteAdvancingMnemonicContext;
import io.proleap.cobol.Cobol85Parser.WriteAdvancingPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.write.Advancing;
import io.proleap.cobol.parser.metamodel.procedure.write.AdvancingLines;
import io.proleap.cobol.parser.metamodel.procedure.write.AdvancingMnemonic;

public class AdvancingImpl extends CobolDivisionElementImpl implements Advancing {

	protected AdvancingLines advancingLines;

	protected AdvancingMnemonic advancingMnemonic;

	protected WriteAdvancingPhraseContext ctx;

	protected PositionType positionType;

	protected Type type;

	public AdvancingImpl(final ProgramUnit programUnit, final WriteAdvancingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AdvancingLines addAdvancingLines(final WriteAdvancingLinesContext ctx) {
		AdvancingLines result = (AdvancingLines) getASGElement(ctx);

		if (result == null) {
			result = new AdvancingLinesImpl(programUnit, ctx);

			final Call linesCall = createCall(ctx.identifier(), ctx.literal());
			result.setLinesCall(linesCall);

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
	public PositionType getPositionType() {
		return positionType;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setPositionType(final PositionType positionType) {
		this.positionType = positionType;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
