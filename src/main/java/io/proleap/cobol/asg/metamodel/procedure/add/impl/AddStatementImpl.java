/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddFromContext;
import io.proleap.cobol.Cobol85Parser.AddGivingContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToContext;
import io.proleap.cobol.Cobol85Parser.AddToGivingContext;
import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.add.AddCorrespondingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.To;
import io.proleap.cobol.asg.metamodel.procedure.add.ToGiving;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AddStatementImpl extends StatementImpl implements AddStatement {

	protected AddCorrespondingStatement addCorrespondingStatement;

	protected AddToGivingStatement addToGivingStatement;

	protected AddToStatement addToStatement;

	protected AddType addType;

	protected final AddStatementContext ctx;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected final StatementType statementType = StatementTypeEnum.ADD;

	public AddStatementImpl(final ProgramUnit programUnit, final Scope scope, final AddStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AddCorrespondingStatement addAddCorrespondingStatement(final AddCorrespondingStatementContext ctx) {
		AddCorrespondingStatement result = (AddCorrespondingStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddCorrespondingStatementImpl(programUnit, ctx);

			/*
			 * from
			 */
			if (ctx.identifier() != null) {
				final Call fromCall = createCall(ctx.identifier());
				result.setFrom(fromCall);
			}

			/*
			 * to
			 */
			final To to = createTo(ctx.addTo());
			result.setTo(to);

			addCorrespondingStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddToGivingStatement addAddToGivingStatement(final AddToGivingStatementContext ctx) {
		AddToGivingStatement result = (AddToGivingStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddToGivingStatementImpl(programUnit, ctx);

			/*
			 * from
			 */
			for (final AddFromContext fromContext : ctx.addFrom()) {
				final From from = createFrom(fromContext);
				result.addFrom(from);
			}

			/*
			 * to
			 */
			for (final AddToGivingContext toGivingContext : ctx.addToGiving()) {
				final ToGiving to = createToGiving(toGivingContext);
				result.addTo(to);
			}

			/*
			 * giving
			 */
			for (final AddGivingContext givingContext : ctx.addGiving()) {
				final Giving giving = createGiving(givingContext);
				result.addGiving(giving);
			}

			addToGivingStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddToStatement addAddToStatement(final AddToStatementContext ctx) {
		AddToStatement result = (AddToStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddToStatementImpl(programUnit, ctx);

			/*
			 * from
			 */
			for (final AddFromContext fromContext : ctx.addFrom()) {
				final From from = createFrom(fromContext);
				result.addFrom(from);
			}

			/*
			 * to
			 */
			for (final AddToContext toContext : ctx.addTo()) {
				final To to = createTo(toContext);
				result.addTo(to);
			}

			addToStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	protected From createFrom(final AddFromContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			/*
			 * from
			 */
			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setFrom(fromValueStmt);

			registerASGElement(result);
		}

		return result;
	}

	protected Giving createGiving(final AddGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			/*
			 * giving
			 */
			if (ctx.identifier() != null) {
				final Call giving = createCall(ctx.identifier());
				result.setGiving(giving);
			}

			/*
			 * rounded
			 */
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected To createTo(final AddToContext ctx) {
		To result = (To) getASGElement(ctx);

		if (result == null) {
			result = new ToImpl(programUnit, ctx);

			/*
			 * to
			 */
			if (ctx.identifier() != null) {
				final Call to = createCall(ctx.identifier());
				result.setTo(to);
			}

			/*
			 * rounded
			 */
			if (ctx.ROUNDED() != null) {
				result.setRounded(true);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected ToGiving createToGiving(final AddToGivingContext ctx) {
		ToGiving result = (ToGiving) getASGElement(ctx);

		if (result == null) {
			result = new ToGivingImpl(programUnit, ctx);

			/*
			 * to
			 */
			final ValueStmt to = createValueStmt(ctx.identifier(), ctx.literal());
			result.setTo(to);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddCorrespondingStatement getAddCorrespondingStatement() {
		return addCorrespondingStatement;
	}

	@Override
	public AddToGivingStatement getAddToGivingStatement() {
		return addToGivingStatement;
	}

	@Override
	public AddToStatement getAddToStatement() {
		return addToStatement;
	}

	@Override
	public AddType getAddType() {
		return addType;
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
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setAddType(final AddType addType) {
		this.addType = addType;
	}

	@Override
	public void setNotOnSizePhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

}
