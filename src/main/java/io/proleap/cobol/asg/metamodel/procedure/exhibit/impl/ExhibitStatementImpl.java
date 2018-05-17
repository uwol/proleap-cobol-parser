/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exhibit.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ExhibitOperandContext;
import io.proleap.cobol.CobolParser.ExhibitStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.ExhibitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.Operand;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ExhibitStatementImpl extends StatementImpl implements ExhibitStatement {

	protected boolean changed;

	protected final ExhibitStatementContext ctx;

	protected boolean named;

	protected List<Operand> operands = new ArrayList<Operand>();

	protected final StatementType statementType = StatementTypeEnum.EXHIBIT;

	public ExhibitStatementImpl(final ProgramUnit programUnit, final Scope scope, final ExhibitStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Operand addOperand(final ExhibitOperandContext ctx) {
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
	public List<Operand> getOperands() {
		return operands;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	@Override
	public boolean isNamed() {
		return named;
	}

	@Override
	public void setChanged(final boolean changed) {
		this.changed = changed;
	}

	@Override
	public void setNamed(final boolean named) {
		this.named = named;
	}

}
