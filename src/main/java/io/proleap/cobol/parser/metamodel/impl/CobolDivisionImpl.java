/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.IntegerLiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.parser.util.StringUtils;

public abstract class CobolDivisionImpl extends ProgramUnitElementImpl implements CobolDivision {

	private final static Logger LOG = LogManager.getLogger(CobolDivisionImpl.class);

	public CobolDivisionImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

	@Override
	public Call addCall(final AssignmentNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, this, ctx);
		}

		return result;
	}

	@Override
	public Call addCall(final CobolWordContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, this, ctx);
		}

		return result;
	}

	@Override
	public Call addCall(final IdentifierContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final DataDescriptionEntry dataDescriptionEntry = programUnit.getDataDivision()
					.getDataDescriptionEntry(name);

			/*
			 * create call model element
			 */
			if (dataDescriptionEntry != null) {
				final DataDescriptionEntryCall dataDescriptionEntryCall = new DataDescriptionEntryCallImpl(name,
						dataDescriptionEntry, programUnit, this, ctx);

				associateDataDescriptionEntryCallWithDataDescriptionEntry(dataDescriptionEntryCall,
						dataDescriptionEntry);

				result = dataDescriptionEntryCall;
			} else {
				LOG.warn("call to unknown element {}", name);
				result = new UndefinedCallImpl(name, programUnit, this, ctx);
			}
		}

		return result;
	}

	@Override
	public Call addCall(final ProcedureNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Paragraph paragraph = programUnit.getProcedureDivision().getParagraph(name);

			if (paragraph != null) {
				final ProcedureCall procedureCall = new ProcedureCallImpl(name, paragraph, programUnit, this, ctx);

				associateProcedureCallWithParagraph(procedureCall, paragraph);

				result = procedureCall;
			} else {
				result = new UndefinedCallImpl(name, programUnit, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IntegerLiteral addIntegerLiteral(final IntegerLiteralContext ctx) {
		IntegerLiteral result = (IntegerLiteral) getASGElement(ctx);

		if (result == null) {
			final Integer value = StringUtils.parseInteger(ctx.getText());
			result = new IntegerLiteralImpl(value, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Literal addLiteral(final LiteralContext ctx) {
		Literal result = (Literal) getASGElement(ctx);

		if (result == null) {
			final String value = ctx.getText();
			result = new LiteralImpl(value, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected void associateDataDescriptionEntryCallWithDataDescriptionEntry(
			final DataDescriptionEntryCall dataDescriptionEntryCall, final DataDescriptionEntry dataDescriptionEntry) {
		dataDescriptionEntry.addDataDescriptionEntryCall(dataDescriptionEntryCall);
	}

	protected void associateProcedureCallWithParagraph(final ProcedureCall procedureCall, final Paragraph paragraph) {
		paragraph.addProcedureCall(procedureCall);
	}

	protected CallValueStmt createCallValueStmt(final AssignmentNameContext ctx) {
		final Call delegatedCall = addCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, this, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final CobolWordContext ctx) {
		final Call delegatedCall = addCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, this, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final IdentifierContext ctx) {
		final Call delegatedCall = addCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, this, ctx);
		return result;
	}

	protected IntegerLiteralValueStmt createIntegerLiteralValueStmt(final IntegerLiteralContext ctx) {
		final IntegerLiteral integerLiteral = addIntegerLiteral(ctx);
		final IntegerLiteralValueStmt result = new IntegerLiteralValueStmtImpl(programUnit, this, ctx);
		result.setIntegerLiteral(integerLiteral);
		return result;
	}

	protected LiteralValueStmt createLiteralValueStmt(final LiteralContext ctx) {
		final Literal literal = addLiteral(ctx);
		final LiteralValueStmt result = new LiteralValueStmtImpl(programUnit, this, ctx);
		result.setLiteral(literal);
		return result;
	}

}
