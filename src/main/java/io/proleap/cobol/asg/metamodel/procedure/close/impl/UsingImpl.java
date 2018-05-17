/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.CobolParser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingCloseDispositionContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.AssociatedDataLengthPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.AssociatedDataPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseDispositionPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.Using;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class UsingImpl extends CobolDivisionElementImpl implements Using {

	protected AssociatedDataLengthPhrase associatedDataLengthPhrase;

	protected AssociatedDataPhrase associatedDataPhrase;

	protected CloseDispositionPhrase closeDispositionPhrase;

	protected ClosePortFileIOUsingContext ctx;

	protected UsingType usingType;

	public UsingImpl(final ProgramUnit programUnit, final ClosePortFileIOUsingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AssociatedDataLengthPhrase addAssociatedDataLengthPhrase(
			final ClosePortFileIOUsingAssociatedDataLengthContext ctx) {
		AssociatedDataLengthPhrase result = (AssociatedDataLengthPhrase) getASGElement(ctx);

		if (result == null) {
			result = new AssociatedDataLengthPhraseImpl(programUnit, ctx);

			// data call
			final ValueStmt dataLengthValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setDataLengthValueStmt(dataLengthValueStmt);

			associatedDataLengthPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AssociatedDataPhrase addAssociatedDataPhrase(final ClosePortFileIOUsingAssociatedDataContext ctx) {
		AssociatedDataPhrase result = (AssociatedDataPhrase) getASGElement(ctx);

		if (result == null) {
			result = new AssociatedDataPhraseImpl(programUnit, ctx);

			// data
			final ValueStmt dataValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setDataValueStmt(dataValueStmt);

			associatedDataPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CloseDispositionPhrase addCloseDispositionPhrase(final ClosePortFileIOUsingCloseDispositionContext ctx) {
		CloseDispositionPhrase result = (CloseDispositionPhrase) getASGElement(ctx);

		if (result == null) {
			result = new CloseDispositionPhraseImpl(programUnit, ctx);

			// type
			final CloseDispositionPhrase.UsingCloseDispositionType type;

			if (ctx.ABORT() != null) {
				type = CloseDispositionPhrase.UsingCloseDispositionType.ABORT;
			} else if (ctx.ORDERLY() != null) {
				type = CloseDispositionPhrase.UsingCloseDispositionType.ORDERLY;
			} else {
				type = null;
			}

			result.setUsingCloseDispositionType(type);

			closeDispositionPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AssociatedDataLengthPhrase getAssociatedDataLengthPhrase() {
		return associatedDataLengthPhrase;
	}

	@Override
	public AssociatedDataPhrase getAssociatedDataPhrase() {
		return associatedDataPhrase;
	}

	@Override
	public CloseDispositionPhrase getCloseDispositionPhrase() {
		return closeDispositionPhrase;
	}

	@Override
	public UsingType getUsingType() {
		return usingType;
	}

	@Override
	public void setUsingType(final UsingType usingType) {
		this.usingType = usingType;
	}

}
