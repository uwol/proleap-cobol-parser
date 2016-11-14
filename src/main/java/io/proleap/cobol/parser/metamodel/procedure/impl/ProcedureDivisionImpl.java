/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.procedure.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ParagraphName;
import io.proleap.cobol.parser.metamodel.procedure.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.procedure.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.StopStatement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.ValueStmtDelegateImpl;

public class ProcedureDivisionImpl extends CobolDivisionImpl implements ProcedureDivision {

	private final static Logger LOG = LogManager.getLogger(ProcedureDivisionImpl.class);

	protected final ProcedureDivisionContext ctx;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, Paragraph> paragraphsByName = new HashMap<String, Paragraph>();

	public ProcedureDivisionImpl(final ProgramUnit programUnit, final ProcedureDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	protected List<Call> addCallsThrough(final Call firstCall, final Call lastCall,
			final PerformProcedureStatementContext ctx) {
		final List<Call> result = new ArrayList<Call>();

		final String firstCallName = firstCall.getName();
		final String lastCallName = lastCall.getName();

		boolean inThrough = false;

		for (final Paragraph paragraph : paragraphs) {
			final String paragraphName = paragraph.getName();

			if (paragraphName.equals(lastCallName)) {
				break;
			} else if (paragraphName.equals(firstCallName)) {
				inThrough = true;
			} else if (inThrough) {
				final ProcedureCall procedureCall = new ProcedureCallImpl(paragraphName, paragraph, programUnit, ctx);
				result.add(procedureCall);

				associateProcedureCallWithParagraph(procedureCall, paragraph);
			}
		}

		return result;
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MoveToStatement addMoveToStatement(final MoveToStatementContext ctx) {
		MoveToStatement result = (MoveToStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveToStatementImpl(programUnit, ctx);

			final MoveToStatementSendingAreaContext moveToStatementSendingArea = ctx.moveToStatementSendingArea();
			final List<IdentifierContext> identifierCtxs = ctx.identifier();

			// sending area value statement
			final ValueStmt sendingAreaValueStmt = addValueStmt(moveToStatementSendingArea);
			result.setSendingAreaValueStmt(sendingAreaValueStmt);

			// receiving area calls
			for (final IdentifierContext identifierCtx : identifierCtxs) {
				final Call receivingAreaCall = addCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, programUnit, ctx);

			paragraphs.add(result);
			paragraphsByName.put(name, result);

			final ParagraphName paragraphName = addParagraphName(ctx.paragraphName());
			result.addParagraphName(paragraphName);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ParagraphName addParagraphName(final ParagraphNameContext ctx) {
		ParagraphName result = (ParagraphName) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphNameImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformProcedureStatement addPerformProcedureStatement(final PerformProcedureStatementContext ctx) {
		PerformProcedureStatement result = (PerformProcedureStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformProcedureStatementImpl(programUnit, ctx);

			final List<ProcedureNameContext> procedureNames = ctx.procedureName();

			if (procedureNames.isEmpty()) {
				LOG.warn("no calls in {}.", ctx);
			} else {
				final Call firstCall = addCall(procedureNames.get(0));
				result.addCall(firstCall);

				if (procedureNames.size() > 1) {
					final Call lastCall = addCall(procedureNames.get(1));
					result.addCall(lastCall);

					final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);
					result.addCalls(callsThrough);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformStatement addPerformStatement(final PerformStatementContext ctx) {
		PerformStatement result = (PerformStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformStatementImpl(programUnit, ctx);

			if (ctx.performProcedureStatement() != null) {
				final PerformProcedureStatement performProcedureStatement = addPerformProcedureStatement(
						ctx.performProcedureStatement());
				result.setPerformProcedureStatement(performProcedureStatement);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	public ValueStmt addValueStmt(final LiteralContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new LiteralValueStmtImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final MoveToStatementSendingAreaContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated value stmt
			 */
			final ValueStmt delegatedValueStmt;

			if (ctx.identifier() != null) {
				delegatedValueStmt = createCallValueStmt(ctx.identifier());
			} else if (ctx.literal() != null) {
				delegatedValueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				delegatedValueStmt = null;
			}

			result = new ValueStmtDelegateImpl(delegatedValueStmt, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Paragraph getParagraph(final String name) {
		return paragraphsByName.get(name);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
}
