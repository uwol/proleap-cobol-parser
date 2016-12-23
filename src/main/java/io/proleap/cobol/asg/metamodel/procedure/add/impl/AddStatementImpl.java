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
import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddTo;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.To;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AddStatementImpl extends StatementImpl implements AddStatement {

	protected AddCorresponding addCorresponding;

	protected AddTo addTo;

	protected AddToGiving addToGiving;

	protected final AddStatementContext ctx;

	protected NotOnSizeError notOnSizeError;

	protected OnSizeError onSizeError;

	protected final StatementType statementType = StatementTypeEnum.ADD;

	protected Type type;

	public AddStatementImpl(final ProgramUnit programUnit, final Scope scope, final AddStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public AddCorresponding addAddCorresponding(final AddCorrespondingStatementContext ctx) {
		AddCorresponding result = (AddCorresponding) getASGElement(ctx);

		if (result == null) {
			result = new AddCorrespondingImpl(programUnit, ctx);

			/*
			 * from
			 */
			final Call fromCall = createCall(ctx.identifier());
			result.setFrom(fromCall);

			/*
			 * to
			 */
			final To to = createTo(ctx.addTo());
			result.setTo(to);

			addCorresponding = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddTo addAddTo(final AddToStatementContext ctx) {
		AddTo result = (AddTo) getASGElement(ctx);

		if (result == null) {
			result = new AddToImpl(programUnit, ctx);

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

			addTo = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AddToGiving addAddToGiving(final AddToGivingStatementContext ctx) {
		AddToGiving result = (AddToGiving) getASGElement(ctx);

		if (result == null) {
			result = new AddToGivingImpl(programUnit, ctx);

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

			/*
			 * giving
			 */
			for (final AddGivingContext givingContext : ctx.addGiving()) {
				final Giving giving = createGiving(givingContext);
				result.addGiving(giving);
			}

			addToGiving = result;
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
			final Call giving = createCall(ctx.identifier());
			result.setGiving(giving);

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
			final Call to = createCall(ctx.identifier());
			result.setTo(to);

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

	@Override
	public AddCorresponding getAddCorresponding() {
		return addCorresponding;
	}

	@Override
	public AddTo getAddTo() {
		return addTo;
	}

	@Override
	public AddToGiving getAddToGiving() {
		return addToGiving;
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
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setNotOnSize(final NotOnSizeError notOnSizeError) {
		this.notOnSizeError = notOnSizeError;
	}

	@Override
	public void setOnSizeError(final OnSizeError onSizeError) {
		this.onSizeError = onSizeError;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
