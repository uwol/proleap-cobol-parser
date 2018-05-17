/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import java.util.List;

import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.DestinationCountClauseContext;
import io.proleap.cobol.CobolParser.DestinationTableClauseContext;
import io.proleap.cobol.CobolParser.ErrorKeyClauseContext;
import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.CobolParser.StatusKeyClauseContext;
import io.proleap.cobol.CobolParser.SymbolicDestinationClauseContext;
import io.proleap.cobol.CobolParser.TextLengthClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.asg.metamodel.data.communication.DestinationCountClause;
import io.proleap.cobol.asg.metamodel.data.communication.DestinationTableClause;
import io.proleap.cobol.asg.metamodel.data.communication.ErrorKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicDestinationClause;
import io.proleap.cobol.asg.metamodel.data.communication.TextLengthClause;

public class CommunicationDescriptionEntryOutputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryOutput {

	protected final CommunicationDescriptionEntryFormat2Context ctx;

	protected DestinationCountClause destinationCountClause;

	protected DestinationTableClause destinationTableClause;

	protected ErrorKeyClause errorKeyClause;

	protected StatusKeyClause statusKeyClause;

	protected SymbolicDestinationClause symbolicDestinationClause;

	protected TextLengthClause textLengthClause;

	public CommunicationDescriptionEntryOutputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DestinationCountClause addDestinationCountClause(final DestinationCountClauseContext ctx) {
		DestinationCountClause result = (DestinationCountClause) getASGElement(ctx);

		if (result == null) {
			result = new DestinationCountClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			destinationCountClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DestinationTableClause addDestinationTableClause(final DestinationTableClauseContext ctx) {
		DestinationTableClause result = (DestinationTableClause) getASGElement(ctx);

		if (result == null) {
			result = new DestinationTableClauseImpl(programUnit, ctx);

			/*
			 * occurs
			 */
			if (ctx.integerLiteral() != null) {
				final IntegerLiteral occursIntegerLiteral = createIntegerLiteral(ctx.integerLiteral());
				result.setIntegerLiteral(occursIntegerLiteral);
			}

			/*
			 * indexes
			 */
			final List<IndexNameContext> indexNameContexts = ctx.indexName();

			for (final IndexNameContext indexNameContext : indexNameContexts) {
				final Call indexCall = createCall(indexNameContext);
				result.addIndexCall(indexCall);
			}

			destinationTableClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ErrorKeyClause addErrorKeyClause(final ErrorKeyClauseContext ctx) {
		ErrorKeyClause result = (ErrorKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new ErrorKeyClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			errorKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public StatusKeyClause addStatusKeyClause(final StatusKeyClauseContext ctx) {
		StatusKeyClause result = (StatusKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new StatusKeyClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			statusKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SymbolicDestinationClause addSymbolicDestinationClause(final SymbolicDestinationClauseContext ctx) {
		SymbolicDestinationClause result = (SymbolicDestinationClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicDestinationClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			symbolicDestinationClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TextLengthClause addTextLengthClause(final TextLengthClauseContext ctx) {
		TextLengthClause result = (TextLengthClause) getASGElement(ctx);

		if (result == null) {
			result = new TextLengthClauseImpl(programUnit, ctx);

			final Call dataDescCall = createCall(ctx.dataDescName());
			result.setDataDescCall(dataDescCall);

			textLengthClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntryType getCommunicationDescriptionEntryType() {
		return CommunicationDescriptionEntryType.OUTPUT;
	}

	@Override
	public DestinationCountClause getDestinationCountClause() {
		return destinationCountClause;
	}

	@Override
	public DestinationTableClause getDestinationTableClause() {
		return destinationTableClause;
	}

	@Override
	public ErrorKeyClause getErrorKeyClause() {
		return errorKeyClause;
	}

	@Override
	public StatusKeyClause getStatusKeyClause() {
		return statusKeyClause;
	}

	@Override
	public SymbolicDestinationClause getSymbolicDestinationClause() {
		return symbolicDestinationClause;
	}

	@Override
	public TextLengthClause getTextLengthClause() {
		return textLengthClause;
	}

}
