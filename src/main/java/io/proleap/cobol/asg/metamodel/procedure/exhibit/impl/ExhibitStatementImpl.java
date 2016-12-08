/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.exhibit.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ExhibitOperandContext;
import io.proleap.cobol.Cobol85Parser.ExhibitStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.ExhibitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.Operand;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class ExhibitStatementImpl extends StatementImpl implements ExhibitStatement {

	protected boolean changed;

	protected final ExhibitStatementContext ctx;

	protected boolean named;

	protected List<Operand> operands = new ArrayList<Operand>();

	protected final StatementType statementType = StatementTypeEnum.Exhibit;

	public ExhibitStatementImpl(final ProgramUnit programUnit, final Scope scope, final ExhibitStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Operand addOperand(final ExhibitOperandContext ctx) {
		Operand result = (Operand) getASGElement(ctx);

		if (result == null) {
			result = new OperandImpl(programUnit, ctx);

			final Call operandCall = createCall(ctx.identifier(), ctx.literal());
			result.setOperandCall(operandCall);

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
