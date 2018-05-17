/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.LibraryAttributeClauseFormat2Context;
import io.proleap.cobol.CobolParser.LibraryAttributeFunctionContext;
import io.proleap.cobol.CobolParser.LibraryAttributeParameterContext;
import io.proleap.cobol.CobolParser.LibraryAttributeTitleContext;
import io.proleap.cobol.CobolParser.LibraryDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.LibraryEntryProcedureClauseFormat2Context;
import io.proleap.cobol.CobolParser.LibraryIsCommonClauseContext;
import io.proleap.cobol.CobolParser.LibraryIsGlobalClauseContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.programlibrary.CommonClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryImport;

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
			final ImportAttribute.ImportAttributeType type;

			if (ctx.BYFUNCTION() != null) {
				type = ImportAttribute.ImportAttributeType.BY_FUNCTION;
			} else if (ctx.BYTITLE() != null) {
				type = ImportAttribute.ImportAttributeType.BY_TITLE;
			} else {
				type = null;
			}

			result.setImportAttributeType(type);

			/*
			 * function
			 */
			if (ctx.libraryAttributeFunction() != null) {
				final LibraryAttributeFunctionContext libraryAttributeFunction = ctx.libraryAttributeFunction();

				if (libraryAttributeFunction.literal() != null) {
					final Literal functionLiteral = createLiteral(libraryAttributeFunction.literal());
					result.setFunctionLiteral(functionLiteral);
				}
			}

			/*
			 * parameter
			 */
			if (ctx.libraryAttributeParameter() != null) {
				final LibraryAttributeParameterContext libraryAttributeParameter = ctx.libraryAttributeParameter();

				if (libraryAttributeParameter != null) {
					final Literal parameterLiteral = createLiteral(libraryAttributeParameter.literal());
					result.setParameterLiteral(parameterLiteral);
				}
			}

			/*
			 * title
			 */
			if (ctx.libraryAttributeTitle() != null) {
				final LibraryAttributeTitleContext libraryAttributeTitle = ctx.libraryAttributeTitle();

				if (libraryAttributeTitle.literal() != null) {
					final Literal titleLiteral = createLiteral(libraryAttributeTitle.literal());
					result.setTitleLiteral(titleLiteral);
				}
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
	public LibraryDescriptionEntryType getLibraryDescriptionEntryType() {
		return LibraryDescriptionEntryType.IMPORT;
	}

}
