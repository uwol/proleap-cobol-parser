/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import io.proleap.cobol.CobolParser.LibraryEntryProcedureClauseFormat2Context;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureUsingClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureUsingNameContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureWithClauseContext;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureWithNameContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ForClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.GivingClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.UsingClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.WithClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ImportEntryProcedureImpl extends CobolDivisionElementImpl implements ImportEntryProcedure {

	protected LibraryEntryProcedureClauseFormat2Context ctx;

	protected ForClause forClause;

	protected GivingClause givingClause;

	protected Call programCall;

	protected UsingClause usingClause;

	protected WithClause withClause;

	public ImportEntryProcedureImpl(final ProgramUnit programUnit,
			final LibraryEntryProcedureClauseFormat2Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ForClause addForClause(final LibraryEntryProcedureForClauseContext ctx) {
		ForClause result = (ForClause) getASGElement(ctx);

		if (result == null) {
			result = new ForClauseImpl(programUnit, ctx);

			if (ctx.literal() != null) {
				final Literal forLiteral = createLiteral(ctx.literal());
				result.setForLiteral(forLiteral);
			}

			forClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingClause addGivingClause(final LibraryEntryProcedureGivingClauseContext ctx) {
		GivingClause result = (GivingClause) getASGElement(ctx);

		if (result == null) {
			result = new GivingClauseImpl(programUnit, ctx);

			final Call givingCall = createCall(ctx.dataName());
			result.setGivingCall(givingCall);

			givingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingClause addUsingClause(final LibraryEntryProcedureUsingClauseContext ctx) {
		UsingClause result = (UsingClause) getASGElement(ctx);

		if (result == null) {
			result = new UsingClauseImpl(programUnit, ctx);

			for (final LibraryEntryProcedureUsingNameContext libraryEntryProcedureUsingNameContext : ctx
					.libraryEntryProcedureUsingName()) {
				final ValueStmt withValueStmt = createValueStmt(libraryEntryProcedureUsingNameContext.dataName(),
						libraryEntryProcedureUsingNameContext.fileName());
				result.addUsingValueStmt(withValueStmt);
			}

			usingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public WithClause addWithClause(final LibraryEntryProcedureWithClauseContext ctx) {
		WithClause result = (WithClause) getASGElement(ctx);

		if (result == null) {
			result = new WithClauseImpl(programUnit, ctx);

			for (final LibraryEntryProcedureWithNameContext libraryEntryProcedureWithNameContext : ctx
					.libraryEntryProcedureWithName()) {
				final ValueStmt withValueStmt = createValueStmt(libraryEntryProcedureWithNameContext.localName(),
						libraryEntryProcedureWithNameContext.fileName());
				result.addWithValueStmt(withValueStmt);
			}

			withClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ForClause getForClause() {
		return forClause;
	}

	@Override
	public GivingClause getGivingClause() {
		return givingClause;
	}

	@Override
	public Call getProgramCall() {
		return programCall;
	}

	@Override
	public UsingClause getUsingClause() {
		return usingClause;
	}

	@Override
	public WithClause getWithClause() {
		return withClause;
	}

	@Override
	public void setProgramCall(final Call programCall) {
		this.programCall = programCall;
	}

}
