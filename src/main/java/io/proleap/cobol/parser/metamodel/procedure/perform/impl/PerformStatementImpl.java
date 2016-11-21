/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.perform.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;

public class PerformStatementImpl extends StatementImpl implements PerformStatement {

	private final static Logger LOG = LogManager.getLogger(PerformStatementImpl.class);

	protected final PerformStatementContext ctx;

	protected PerformProcedureStatement performProcedureStatement;

	public PerformStatementImpl(final ProgramUnit programUnit, final PerformStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	protected List<Call> addCallsThrough(final Call firstCall, final Call lastCall,
			final PerformProcedureStatementContext ctx) {
		final List<Call> result = new ArrayList<Call>();

		final String firstCallName = firstCall.getName();
		final String lastCallName = lastCall.getName();

		boolean inThrough = false;

		final List<Paragraph> paragraphs = programUnit.getProcedureDivision().getParagraphs();

		for (final Paragraph paragraph : paragraphs) {
			final String paragraphName = paragraph.getName();

			if (paragraphName.equals(lastCallName)) {
				break;
			} else if (paragraphName.equals(firstCallName)) {
				inThrough = true;
			} else if (inThrough) {
				final ProcedureCall call = new ProcedureCallImpl(paragraphName, paragraph, programUnit, ctx);
				result.add(call);

				associateProcedureCallWithParagraph(call, paragraph);
			}
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
	public PerformProcedureStatement getPerformProcedureStatement() {
		return performProcedureStatement;
	}

}
