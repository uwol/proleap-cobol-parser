/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.InitializeReplacingByContext;
import io.proleap.cobol.Cobol85Parser.InitializeReplacingPhraseContext;
import io.proleap.cobol.Cobol85Parser.InitializeStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.Replacing;

public class InitializeStatementImpl extends StatementImpl implements InitializeStatement {

	protected final InitializeStatementContext ctx;

	protected List<Call> dataItemCalls = new ArrayList<Call>();

	protected Replacing replacing;

	protected final StatementType statementType = StatementTypeEnum.Initialize;

	public InitializeStatementImpl(final ProgramUnit programUnit, final Scope scope,
			final InitializeStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataItemCall(final Call dataItemCall) {
		dataItemCalls.add(dataItemCall);
	}

	@Override
	public Replacing addReplacing(final InitializeReplacingPhraseContext ctx) {
		Replacing result = (Replacing) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingImpl(programUnit, ctx);

			// by
			for (final InitializeReplacingByContext initializeReplacingByContext : ctx.initializeReplacingBy()) {
				result.addBy(initializeReplacingByContext);
			}

			replacing = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getDataItemCalls() {
		return dataItemCalls;
	}

	@Override
	public Replacing getReplacing() {
		return replacing;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
