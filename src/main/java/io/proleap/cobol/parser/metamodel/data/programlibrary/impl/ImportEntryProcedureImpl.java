/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureUsingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureUsingNameContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureWithClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureWithNameContext;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ForClause;
import io.proleap.cobol.parser.metamodel.data.programlibrary.GivingClause;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.parser.metamodel.data.programlibrary.UsingClause;
import io.proleap.cobol.parser.metamodel.data.programlibrary.WithClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ImportEntryProcedureImpl extends CobolDivisionElementImpl implements ImportEntryProcedure {

	protected LibraryEntryProcedureClauseFormat2Context ctx;

	protected ForClause forClause;

	protected GivingClause givingClause;

	protected ValueStmt programValueStmt;

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

			final Literal forLiteral = createLiteral(ctx.literal());
			result.setForLiteral(forLiteral);

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

			final CallValueStmt givingValueStmt = createCallValueStmt(ctx.dataName());
			result.setGivingValueStmt(givingValueStmt);

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
	public ValueStmt getProgramValueStmt() {
		return programValueStmt;
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
	public void setProgramValueStmt(final ValueStmt programValueStmt) {
		this.programValueStmt = programValueStmt;
	}

}
