/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import io.proleap.cobol.CobolParser.AddCorrespondingStatementContext;
import io.proleap.cobol.CobolParser.AddFromContext;
import io.proleap.cobol.CobolParser.AddGivingContext;
import io.proleap.cobol.CobolParser.AddStatementContext;
import io.proleap.cobol.CobolParser.AddToContext;
import io.proleap.cobol.CobolParser.AddToGivingContext;
import io.proleap.cobol.CobolParser.AddToGivingStatementContext;
import io.proleap.cobol.CobolParser.AddToStatementContext;
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
				result.setFromCall(fromCall);
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
			result.setFromValueStmt(fromValueStmt);

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
				final Call givingCall = createCall(ctx.identifier());
				result.setGivingCall(givingCall);
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
				final Call toCall = createCall(ctx.identifier());
				result.setToCall(toCall);
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
			final ValueStmt toValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setToValueStmt(toValueStmt);

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
