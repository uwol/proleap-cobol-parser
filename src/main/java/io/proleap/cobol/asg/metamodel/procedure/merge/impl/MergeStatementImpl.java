/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AlphabetNameContext;
import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.MergeCollatingSequencePhraseContext;
import io.proleap.cobol.CobolParser.MergeGivingContext;
import io.proleap.cobol.CobolParser.MergeGivingPhraseContext;
import io.proleap.cobol.CobolParser.MergeOnKeyClauseContext;
import io.proleap.cobol.CobolParser.MergeOutputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.MergeStatementContext;
import io.proleap.cobol.CobolParser.MergeUsingContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.CollatingSequencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.OnKey;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputProcedurePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputThrough;
import io.proleap.cobol.asg.metamodel.procedure.merge.UsingPhrase;

public class MergeStatementImpl extends StatementImpl implements MergeStatement {

	protected CollatingSequencePhrase collatingSequencePhrase;

	protected final MergeStatementContext ctx;

	protected Call fileCall;

	protected List<GivingPhrase> givingPhrases = new ArrayList<GivingPhrase>();

	protected List<OnKey> onKeys = new ArrayList<OnKey>();

	protected OutputProcedurePhrase outputProcedurePhrase;

	protected final StatementType statementType = StatementTypeEnum.MERGE;

	protected List<UsingPhrase> usingPhrases = new ArrayList<UsingPhrase>();

	public MergeStatementImpl(final ProgramUnit programUnit, final Scope scope, final MergeStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CollatingSequencePhrase addCollatingSequencePhrase(final MergeCollatingSequencePhraseContext ctx) {
		CollatingSequencePhrase result = (CollatingSequencePhrase) getASGElement(ctx);

		if (result == null) {
			result = new CollatingSequenceImpl(programUnit, ctx);

			// alphabet calls
			for (final AlphabetNameContext alphabetNameContext : ctx.alphabetName()) {
				final Call alphabetCall = createCall(alphabetNameContext);
				result.addAlphabetCall(alphabetCall);
			}

			// alphanumeric
			if (ctx.mergeCollatingAlphanumeric() != null) {
				result.addAlphanumeric(ctx.mergeCollatingAlphanumeric());
			}

			// national
			if (ctx.mergeCollatingNational() != null) {
				result.addNational(ctx.mergeCollatingNational());
			}

			collatingSequencePhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingPhrase addGiving(final MergeGivingPhraseContext ctx) {
		GivingPhrase result = (GivingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new GivingPhraseImpl(programUnit, ctx);

			// givings
			for (final MergeGivingContext mergeGivingContext : ctx.mergeGiving()) {
				result.addGiving(mergeGivingContext);
			}

			givingPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OnKey addOnKey(final MergeOnKeyClauseContext ctx) {
		OnKey result = (OnKey) getASGElement(ctx);

		if (result == null) {
			result = new OnKeyImpl(programUnit, ctx);

			// type
			final OnKey.OnKeyType type;

			if (ctx.ASCENDING() != null) {
				type = OnKey.OnKeyType.ASCENDING;
			} else if (ctx.DESCENDING() != null) {
				type = OnKey.OnKeyType.DESCENDING;
			} else {
				type = null;
			}

			// key call
			for (final QualifiedDataNameContext qualifiedDataNameContext : ctx.qualifiedDataName()) {
				final Call keyCall = createCall(qualifiedDataNameContext);
				result.adKeyCall(keyCall);
			}

			result.setOnKeyType(type);

			onKeys.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OutputProcedurePhrase addOutputProcedurePhrase(final MergeOutputProcedurePhraseContext ctx) {
		OutputProcedurePhrase result = (OutputProcedurePhrase) getASGElement(ctx);

		if (result == null) {
			result = new OutputProcedurePhraseImpl(programUnit, ctx);

			// procedure
			final Call firstCall = createCall(ctx.procedureName());
			result.setProcedureCall(firstCall);
			result.addCall(firstCall);

			// through
			if (ctx.mergeOutputThrough() != null) {
				result.addOutputThrough(ctx.mergeOutputThrough());

				final OutputThrough outputThrough = result.getOutputThrough();
				final Call lastCall = outputThrough.getProcedureCall();
				final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);

				result.addCalls(callsThrough);
				result.addCall(lastCall);
			}

			outputProcedurePhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingPhrase addUsingPhrase(final MergeUsingContext ctx) {
		UsingPhrase result = (UsingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new UsingPhraseImpl(programUnit, ctx);

			// file calls
			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			usingPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CollatingSequencePhrase getCollatingSequencePhrase() {
		return collatingSequencePhrase;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public List<GivingPhrase> getGivingPhrases() {
		return givingPhrases;
	}

	@Override
	public List<OnKey> getOnKeys() {
		return onKeys;
	}

	@Override
	public OutputProcedurePhrase getOutputProcedurePhrase() {
		return outputProcedurePhrase;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public List<UsingPhrase> getUsingPhrases() {
		return usingPhrases;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
