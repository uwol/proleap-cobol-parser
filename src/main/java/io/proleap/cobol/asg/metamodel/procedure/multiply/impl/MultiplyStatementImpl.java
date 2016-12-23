/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.Cobol85Parser.MultiplyGivingContext;
import io.proleap.cobol.Cobol85Parser.MultiplyGivingResultContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularOperandContext;
import io.proleap.cobol.Cobol85Parser.MultiplyStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Giving;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Regular;

public class MultiplyStatementImpl extends StatementImpl implements MultiplyStatement {

	protected final MultiplyStatementContext ctx;

	protected Giving giving;

	protected NotOnSizeError notOnSizeError;

	protected OnSizeError onSizeError;

	protected Call operandCall;

	protected Regular regular;

	protected final StatementType statementType = StatementTypeEnum.MULTIPLY;

	protected Type type;

	public MultiplyStatementImpl(final ProgramUnit programUnit, final Scope scope, final MultiplyStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final MultiplyGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// giving operand
			result.addOperand(ctx.multiplyGivingOperand());

			// giving results
			for (final MultiplyGivingResultContext multiplyGivingResultContext : ctx.multiplyGivingResult()) {
				result.addResult(multiplyGivingResultContext);
			}

			giving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Regular addRegular(final MultiplyRegularContext ctx) {
		Regular result = (Regular) getASGElement(ctx);

		if (result == null) {
			result = new RegularImpl(programUnit, ctx);

			for (final MultiplyRegularOperandContext multiplyRegularOperandContext : ctx.multiplyRegularOperand()) {
				result.addOperand(multiplyRegularOperandContext);
			}

			regular = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Giving getGiving() {
		return giving;
	}

	@Override
	public NotOnSizeError getNotOnSizeError() {
		return notOnSizeError;
	}

	@Override
	public OnSizeError getOnSizeError() {
		return onSizeError;
	}

	@Override
	public Call getOperandCall() {
		return operandCall;
	}

	@Override
	public Regular getRegular() {
		return regular;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setNotOnSizeError(final NotOnSizeError notOnSizeError) {
		this.notOnSizeError = notOnSizeError;
	}

	@Override
	public void setOnSizeError(final OnSizeError onSizeError) {
		this.onSizeError = onSizeError;
	}

	@Override
	public void setOperandCall(final Call operandCall) {
		this.operandCall = operandCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
