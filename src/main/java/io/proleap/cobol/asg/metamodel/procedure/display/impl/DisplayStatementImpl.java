/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.display.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.DisplayAtContext;
import io.proleap.cobol.CobolParser.DisplayOperandContext;
import io.proleap.cobol.CobolParser.DisplayStatementContext;
import io.proleap.cobol.CobolParser.DisplayUponContext;
import io.proleap.cobol.CobolParser.DisplayWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.At;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.procedure.display.Upon;
import io.proleap.cobol.asg.metamodel.procedure.display.With;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DisplayStatementImpl extends StatementImpl implements DisplayStatement {

	protected At at;

	protected final DisplayStatementContext ctx;

	protected NotOnExceptionClause notOnExceptionClause;

	protected OnExceptionClause onExceptionClause;

	protected List<Operand> operands = new ArrayList<Operand>();

	protected final StatementType statementType = StatementTypeEnum.DISPLAY;

	protected Upon upon;

	protected With with;

	public DisplayStatementImpl(final ProgramUnit programUnit, final Scope scope, final DisplayStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public At addAt(final DisplayAtContext ctx) {
		At result = (At) getASGElement(ctx);

		if (result == null) {
			result = new AtImpl(programUnit, ctx);

			final ValueStmt atValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setAtValueStmt(atValueStmt);

			at = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Operand addOperand(final DisplayOperandContext ctx) {
		Operand result = (Operand) getASGElement(ctx);

		if (result == null) {
			result = new OperandImpl(programUnit, ctx);

			final ValueStmt operandValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setOperandValueStmt(operandValueStmt);

			operands.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Upon addUpon(final DisplayUponContext ctx) {
		Upon result = (Upon) getASGElement(ctx);

		if (result == null) {
			result = new UponImpl(programUnit, ctx);

			final Call uponCall = createCall(ctx.mnemonicName());
			result.setUponCall(uponCall);

			upon = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public With addWith(final DisplayWithContext ctx) {
		With result = (With) getASGElement(ctx);

		if (result == null) {
			result = new WithImpl(programUnit, ctx);

			// no advancing
			result.setNoAdvancing(true);

			with = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public At getAt() {
		return at;
	}

	@Override
	public NotOnExceptionClause getNotOnExceptionClause() {
		return notOnExceptionClause;
	}

	@Override
	public OnExceptionClause getOnExceptionClause() {
		return onExceptionClause;
	}

	@Override
	public List<Operand> getOperands() {
		return operands;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Upon getUpon() {
		return upon;
	}

	@Override
	public With getWith() {
		return with;
	}

	@Override
	public void setNotOnExceptionClause(final NotOnExceptionClause notOnExceptionClause) {
		this.notOnExceptionClause = notOnExceptionClause;
	}

	@Override
	public void setOnExceptionClause(final OnExceptionClause onExceptionClause) {
		this.onExceptionClause = onExceptionClause;
	}
}
