/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.LibraryAttributeClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.LibraryIsCommonClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryIsGlobalClauseContext;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.data.programlibrary.CommonClause;
import io.proleap.cobol.parser.metamodel.data.programlibrary.GlobalClause;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.parser.metamodel.data.programlibrary.LibraryDescriptionEntryImport;

public class LibraryDescriptionEntryImportImpl extends LibraryDescriptionEntryImpl
		implements LibraryDescriptionEntryImport {

	protected CommonClause commonClause;

	protected final LibraryDescriptionEntryFormat2Context ctx;

	protected GlobalClause globalClause;

	protected List<ImportAttribute> importAttributes = new ArrayList<ImportAttribute>();

	protected List<ImportEntryProcedure> importEntryProcedures = new ArrayList<ImportEntryProcedure>();

	public LibraryDescriptionEntryImportImpl(final String name, final ProgramUnit programUnit,
			final LibraryDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommonClause addCommonClause(final LibraryIsCommonClauseContext ctx) {
		CommonClause result = (CommonClause) getASGElement(ctx);

		if (result == null) {
			result = new CommonClauseImpl(programUnit, ctx);

			result.setCommon(true);

			commonClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GlobalClause addGlobalClause(final LibraryIsGlobalClauseContext ctx) {
		GlobalClause result = (GlobalClause) getASGElement(ctx);

		if (result == null) {
			result = new GlobalClauseImpl(programUnit, ctx);

			result.setGlobal(true);

			globalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ImportAttribute addImportAttribute(final LibraryAttributeClauseFormat2Context ctx) {
		ImportAttribute result = (ImportAttribute) getASGElement(ctx);

		if (result == null) {
			result = new ImportAttributeImpl(programUnit, ctx);

			/*
			 * type
			 */
			final ImportAttribute.Type type;

			if (ctx.BYFUNCTION() != null) {
				type = ImportAttribute.Type.ByFunction;
			} else if (ctx.BYTITLE() != null) {
				type = ImportAttribute.Type.ByTitle;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * function
			 */
			if (ctx.libraryAttributeFunction() != null) {
				final Literal functionLiteral = createLiteral(ctx.libraryAttributeFunction().literal());
				result.setFunctionLiteral(functionLiteral);
			}

			/*
			 * parameter
			 */
			if (ctx.libraryAttributeParameter() != null) {
				final Literal parameterLiteral = createLiteral(ctx.libraryAttributeParameter().literal());
				result.setParameterLiteral(parameterLiteral);
			}

			/*
			 * title
			 */
			if (ctx.libraryAttributeTitle() != null) {
				final Literal titleLiteral = createLiteral(ctx.libraryAttributeTitle().literal());
				result.setTitleLiteral(titleLiteral);
			}

			importAttributes.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ImportEntryProcedure addImportEntryProcedure(final LibraryEntryProcedureClauseFormat2Context ctx) {
		ImportEntryProcedure result = (ImportEntryProcedure) getASGElement(ctx);

		if (result == null) {
			result = new ImportEntryProcedureImpl(programUnit, ctx);

			/*
			 * program name
			 */
			final Call programCall = createCall(ctx.programName());
			result.setProgramCall(programCall);

			/*
			 * for
			 */
			if (ctx.libraryEntryProcedureForClause() != null) {
				result.addForClause(ctx.libraryEntryProcedureForClause());
			}

			/*
			 * with
			 */
			if (ctx.libraryEntryProcedureWithClause() != null) {
				result.addWithClause(ctx.libraryEntryProcedureWithClause());
			}

			/*
			 * using
			 */
			if (ctx.libraryEntryProcedureUsingClause() != null) {
				result.addUsingClause(ctx.libraryEntryProcedureUsingClause());
			}

			/*
			 * giving
			 */
			if (ctx.libraryEntryProcedureGivingClause() != null) {
				result.addGivingClause(ctx.libraryEntryProcedureGivingClause());
			}

			importEntryProcedures.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommonClause getCommonClause() {
		return commonClause;
	}

	@Override
	public GlobalClause getGlobalClause() {
		return globalClause;
	}

	@Override
	public List<ImportAttribute> getImportAttributes() {
		return importAttributes;
	}

	@Override
	public List<ImportEntryProcedure> getImportEntryProcedures() {
		return importEntryProcedures;
	}

	@Override
	public Type getType() {
		return Type.Import;
	}

}
