/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.add.impl;

import io.proleap.cobol.Cobol85Parser.AddCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddFromContext;
import io.proleap.cobol.Cobol85Parser.AddGivingContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToContext;
import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.AddToStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.parser.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddTo;
import io.proleap.cobol.parser.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.parser.metamodel.procedure.add.From;
import io.proleap.cobol.parser.metamodel.procedure.add.Giving;
import io.proleap.cobol.parser.metamodel.procedure.add.To;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class AddStatementImpl extends StatementImpl implements AddStatement {

	protected AddCorresponding addCorresponding;

	protected AddTo addTo;

	protected AddToGiving addToGiving;

	protected final AddStatementContext ctx;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected Type type;

	public AddStatementImpl(final ProgramUnit programUnit, final AddStatementContext ctx) {
		super(programUnit, ctx);

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
			final ValueStmt fromValueStmt = createCallValueStmt(ctx.identifier());
			result.setFrom(fromValueStmt);

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
			 * value stmt
			 */
			final ValueStmt fromValueStmt;

			if (ctx.identifier() != null) {
				fromValueStmt = createCallValueStmt(ctx.identifier());
			} else if (ctx.literal() != null) {
				fromValueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				fromValueStmt = null;
			}

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
			 * giving value stmt
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
			 * to value stmt
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
	public NotOnSizeErrorPhrase getNotOnSizeErrorPhrase() {
		return notOnSizeErrorPhrase;
	}

	@Override
	public OnSizeErrorPhrase getOnSizeErrorPhrase() {
		return onSizeErrorPhrase;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
