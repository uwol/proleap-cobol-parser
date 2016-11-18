/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DestinationCountClauseContext;
import io.proleap.cobol.Cobol85Parser.DestinationTableClauseContext;
import io.proleap.cobol.Cobol85Parser.ErrorKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.Cobol85Parser.StatusKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicDestinationClauseContext;
import io.proleap.cobol.Cobol85Parser.TextLengthClauseContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationCountClause;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationTableClause;
import io.proleap.cobol.parser.metamodel.data.communication.ErrorKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicDestinationClause;
import io.proleap.cobol.parser.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

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

			final ValueStmt dataDescValueStmt = createCallValueStmt(ctx.dataDescName());
			result.setDataDescValueStmt(dataDescValueStmt);

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
			final IntegerLiteral occursIntegerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(occursIntegerLiteral);

			/*
			 * indexes
			 */
			final List<IndexNameContext> indexNameContexts = ctx.indexName();

			for (final IndexNameContext indexNameContext : indexNameContexts) {
				final CallValueStmt indexValueStmt = createCallValueStmt(indexNameContext);
				result.addIndexValueStmt(indexValueStmt);
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

			final ValueStmt dataDescValueStmt = createCallValueStmt(ctx.dataDescName());
			result.setDataDescValueStmt(dataDescValueStmt);

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

			final ValueStmt dataDescValueStmt = createCallValueStmt(ctx.dataDescName());
			result.setDataDescValueStmt(dataDescValueStmt);

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

			final ValueStmt dataDescValueStmt = createCallValueStmt(ctx.dataDescName());
			result.setDataDescValueStmt(dataDescValueStmt);

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

			final ValueStmt dataDescValueStmt = createCallValueStmt(ctx.dataDescName());
			result.setDataDescValueStmt(dataDescValueStmt);

			textLengthClause = result;
			registerASGElement(result);
		}

		return result;
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

	@Override
	public Type getType() {
		return Type.Output;
	}

}
