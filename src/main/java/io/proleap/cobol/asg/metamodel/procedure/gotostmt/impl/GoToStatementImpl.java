/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt.impl;

import io.proleap.cobol.CobolParser.GoToDependingOnStatementContext;
import io.proleap.cobol.CobolParser.GoToStatementContext;
import io.proleap.cobol.CobolParser.GoToStatementSimpleContext;
import io.proleap.cobol.CobolParser.ProcedureNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.DependingOnPhrase;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.Simple;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class GoToStatementImpl extends StatementImpl implements GoToStatement {

	protected final GoToStatementContext ctx;

	protected DependingOnPhrase dependingOnPhrase;

	protected GoToType goToType;

	protected Simple simple;

	protected final StatementType statementType = StatementTypeEnum.GO_TO;

	public GoToStatementImpl(final ProgramUnit programUnit, final Scope scope, final GoToStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public DependingOnPhrase addDependingOnPhrase(final GoToDependingOnStatementContext ctx) {
		DependingOnPhrase result = (DependingOnPhrase) getASGElement(ctx);

		if (result == null) {
			result = new DependingOnPhraseImpl(programUnit, ctx);

			// procedures
			for (final ProcedureNameContext procedureNameContext : ctx.procedureName()) {
				final Call procedureCall = createCall(procedureNameContext);
				result.addProcedureCall(procedureCall);
			}

			// depending on
			if (ctx.identifier() != null) {
				final Call dependingOnCall = createCall(ctx.identifier());
				result.setDependingOnCall(dependingOnCall);
			}

			// more labels
			if (ctx.MORE_LABELS() != null) {
				result.setMoreLabels(true);
			}

			dependingOnPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Simple addSimple(final GoToStatementSimpleContext ctx) {
		Simple result = (Simple) getASGElement(ctx);

		if (result == null) {
			result = new SimpleImpl(programUnit, ctx);

			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			simple = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DependingOnPhrase getDependingOnPhrase() {
		return dependingOnPhrase;
	}

	@Override
	public GoToType getGoToType() {
		return goToType;
	}

	@Override
	public Simple getSimple() {
		return simple;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setGoToType(final GoToType goToType) {
		this.goToType = goToType;
	}

}
