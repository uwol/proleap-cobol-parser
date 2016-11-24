/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.declaratives.impl;

import io.proleap.cobol.Cobol85Parser.ProcedureDeclarativeContext;
import io.proleap.cobol.Cobol85Parser.ProcedureSectionHeaderContext;
import io.proleap.cobol.Cobol85Parser.UseStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.Declarative;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.SectionHeader;
import io.proleap.cobol.parser.metamodel.procedure.use.UseStatement;
import io.proleap.cobol.parser.metamodel.procedure.use.impl.UseStatementImpl;

public class DeclarativeImpl extends CobolDivisionElementImpl implements Declarative {

	protected final ProcedureDeclarativeContext ctx;

	protected SectionHeader sectionHeader;

	protected UseStatement useStatement;

	public DeclarativeImpl(final ProgramUnit programUnit, final ProcedureDeclarativeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public SectionHeader addSectionHeader(final ProcedureSectionHeaderContext ctx) {
		SectionHeader result = (SectionHeader) getASGElement(ctx);

		if (result == null) {
			result = new SectionHeaderImpl(programUnit, ctx);

			sectionHeader = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UseStatement addUseStatement(final UseStatementContext ctx) {
		UseStatement result = (UseStatement) getASGElement(ctx);

		if (result == null) {
			result = new UseStatementImpl(programUnit, ctx);

			// type
			final UseStatement.Type type;

			if (ctx.useAfterClause() != null) {
				result.addAfter(ctx.useAfterClause());
				type = UseStatement.Type.After;
			} else if (ctx.useDebugClause() != null) {
				result.addDebug(ctx.useDebugClause());
				type = UseStatement.Type.Debug;
			} else {
				type = null;
			}

			result.setType(type);

			useStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SectionHeader getSectionHeader() {
		return sectionHeader;
	}

	@Override
	public UseStatement getUseStament() {
		return useStatement;
	}

}
