/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.EvaluateAlsoSelectContext;
import io.proleap.cobol.Cobol85Parser.EvaluateSelectContext;
import io.proleap.cobol.Cobol85Parser.EvaluateStatementContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenOtherContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenPhraseContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.AlsoSelect;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Select;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenOther;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenPhrase;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class EvaluateStatementImpl extends StatementImpl implements EvaluateStatement {

	protected List<AlsoSelect> alsoSelects = new ArrayList<AlsoSelect>();

	protected final EvaluateStatementContext ctx;

	protected Select select;

	protected final StatementType statementType = StatementTypeEnum.Evaluate;

	protected WhenOther whenOther;

	protected List<WhenPhrase> whenPhrases = new ArrayList<WhenPhrase>();

	public EvaluateStatementImpl(final ProgramUnit programUnit, final Scope scope, final EvaluateStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AlsoSelect addAlsoSelect(final EvaluateAlsoSelectContext ctx) {
		AlsoSelect result = (AlsoSelect) getASGElement(ctx);

		if (result == null) {
			result = new AlsoSelectImpl(programUnit, ctx);

			// select
			final Select select = createSelect(ctx.evaluateSelect());
			result.setSelect(select);

			alsoSelects.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Select addSelect(final EvaluateSelectContext ctx) {
		final Select result = createSelect(ctx);
		select = result;
		return result;
	}

	@Override
	public WhenOther addWhenOther(final EvaluateWhenOtherContext ctx) {
		WhenOther result = (WhenOther) getASGElement(ctx);

		if (result == null) {
			result = new WhenOtherImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			whenOther = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WhenPhrase addWhenPhrase(final EvaluateWhenPhraseContext ctx) {
		WhenPhrase result = (WhenPhrase) getASGElement(ctx);

		if (result == null) {
			result = new WhenPhraseImpl(programUnit, ctx);

			// whens
			for (final EvaluateWhenContext evaluateWhenContext : ctx.evaluateWhen()) {
				result.addWhen(evaluateWhenContext);
			}

			// statements
			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			whenPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	protected Select createSelect(final EvaluateSelectContext ctx) {
		Select result = (Select) getASGElement(ctx);

		if (result == null) {
			result = new SelectImpl(programUnit, ctx);

			// select value stmt
			final ValueStmt selectValueStmt = createValueStmt(ctx.identifier(), ctx.literal(),
					ctx.arithmeticExpression(), ctx.condition());
			result.setSelectValueStmt(selectValueStmt);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<AlsoSelect> getAlsoSelects() {
		return alsoSelects;
	}

	@Override
	public Select getSelect() {
		return select;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public WhenOther getWhenOther() {
		return whenOther;
	}

	@Override
	public List<WhenPhrase> getWhenPhrases() {
		return whenPhrases;
	}
}
