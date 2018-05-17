/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.CobolParser.MultiplyGivingContext;
import io.proleap.cobol.CobolParser.MultiplyGivingResultContext;
import io.proleap.cobol.CobolParser.MultiplyRegularContext;
import io.proleap.cobol.CobolParser.MultiplyRegularOperandContext;
import io.proleap.cobol.CobolParser.MultiplyStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MultiplyStatementImpl extends StatementImpl implements MultiplyStatement {

	protected ByPhrase byPhrase;

	protected final MultiplyStatementContext ctx;

	protected GivingPhrase givingPhrase;

	protected MultiplyType multiplyType;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected ValueStmt operandValueStmt;

	protected final StatementType statementType = StatementTypeEnum.MULTIPLY;

	public MultiplyStatementImpl(final ProgramUnit programUnit, final Scope scope, final MultiplyStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByPhrase addByPhrase(final MultiplyRegularContext ctx) {
		ByPhrase result = (ByPhrase) getASGElement(ctx);

		if (result == null) {
			result = new ByPhraseImpl(programUnit, ctx);

			for (final MultiplyRegularOperandContext multiplyRegularOperandContext : ctx.multiplyRegularOperand()) {
				result.addOperand(multiplyRegularOperandContext);
			}

			byPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingPhrase addGivingPhrase(final MultiplyGivingContext ctx) {
		GivingPhrase result = (GivingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new GivingPhraseImpl(programUnit, ctx);

			// giving operand
			result.addGivingOperand(ctx.multiplyGivingOperand());

			// giving results
			for (final MultiplyGivingResultContext multiplyGivingResultContext : ctx.multiplyGivingResult()) {
				result.addGivingResult(multiplyGivingResultContext);
			}

			givingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ByPhrase getByPhrase() {
		return byPhrase;
	}

	@Override
	public GivingPhrase getGivingPhrase() {
		return givingPhrase;
	}

	@Override
	public MultiplyType getMultiplyType() {
		return multiplyType;
	}

	@Override
	public NotOnSizeErrorPhrase getNotOnSizeErrorPhrase() {
		return notOnSizeErrorPhrase;
	}

	@Override
	public OnSizeErrorPhrase getOnSizeErrorPhrase() {
		return onSizeErrorPhrase;
	}

	@Override
	public ValueStmt getOperandValueStmt() {
		return operandValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setMultiplyType(final MultiplyType multiplyType) {
		this.multiplyType = multiplyType;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

	@Override
	public void setOperandValueStmt(final ValueStmt operandValueStmt) {
		this.operandValueStmt = operandValueStmt;
	}

}
