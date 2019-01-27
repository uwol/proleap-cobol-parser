/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AlphabetNameContext;
import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.CobolParser.SortCollatingSequencePhraseContext;
import io.proleap.cobol.CobolParser.SortDuplicatesPhraseContext;
import io.proleap.cobol.CobolParser.SortGivingContext;
import io.proleap.cobol.CobolParser.SortGivingPhraseContext;
import io.proleap.cobol.CobolParser.SortInputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.SortOnKeyClauseContext;
import io.proleap.cobol.CobolParser.SortOutputProcedurePhraseContext;
import io.proleap.cobol.CobolParser.SortStatementContext;
import io.proleap.cobol.CobolParser.SortUsingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.CollatingSequence;
import io.proleap.cobol.asg.metamodel.procedure.sort.Duplicates;
import io.proleap.cobol.asg.metamodel.procedure.sort.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputThrough;
import io.proleap.cobol.asg.metamodel.procedure.sort.OnKey;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputThrough;
import io.proleap.cobol.asg.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.asg.metamodel.procedure.sort.UsingPhrase;

public class SortStatementImpl extends StatementImpl implements SortStatement {

	protected CollatingSequence collatingSequence;

	protected final SortStatementContext ctx;

	protected Duplicates duplicates;

	protected Call fileCall;

	protected List<GivingPhrase> givingPhrases = new ArrayList<GivingPhrase>();

	protected InputProcedure inputProcedure;

	protected List<OnKey> onKeys = new ArrayList<OnKey>();

	protected OutputProcedure outputProcedure;

	protected final StatementType statementType = StatementTypeEnum.SORT;

	protected List<UsingPhrase> usingPhrases = new ArrayList<UsingPhrase>();

	public SortStatementImpl(final ProgramUnit programUnit, final Scope scope, final SortStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CollatingSequence addCollatingSequence(final SortCollatingSequencePhraseContext ctx) {
		CollatingSequence result = (CollatingSequence) getASGElement(ctx);

		if (result == null) {
			result = new CollatingSequenceImpl(programUnit, ctx);

			// alphabet calls
			for (final AlphabetNameContext alphabetNameContext : ctx.alphabetName()) {
				final Call alphabetCall = createCall(alphabetNameContext);
				result.addAlphabetCall(alphabetCall);
			}

			// alphanumeric
			if (ctx.sortCollatingAlphanumeric() != null) {
				result.addAlphanumeric(ctx.sortCollatingAlphanumeric());
			}

			// national
			if (ctx.sortCollatingNational() != null) {
				result.addNational(ctx.sortCollatingNational());
			}

			collatingSequence = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Duplicates addDuplicates(final SortDuplicatesPhraseContext ctx) {
		Duplicates result = (Duplicates) getASGElement(ctx);

		if (result == null) {
			result = new DuplicatesImpl(programUnit, ctx);

			result.setDuplicatesInOrder(true);

			duplicates = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingPhrase addGivingPhrase(final SortGivingPhraseContext ctx) {
		GivingPhrase result = (GivingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new GivingPhraseImpl(programUnit, ctx);

			// givings
			for (final SortGivingContext sortGivingContext : ctx.sortGiving()) {
				result.addGiving(sortGivingContext);
			}

			givingPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InputProcedure addInputProcedure(final SortInputProcedurePhraseContext ctx) {
		InputProcedure result = (InputProcedure) getASGElement(ctx);

		if (result == null) {
			result = new InputProcedureImpl(programUnit, ctx);

			// procedure
			final Call firstCall = createCall(ctx.procedureName());
			result.setProcedureCall(firstCall);
			result.addCall(firstCall);

			// through
			if (ctx.sortInputThrough() != null) {
				result.addInputThrough(ctx.sortInputThrough());

				final InputThrough inputThrough = result.getInputThrough();
				final Call lastCall = inputThrough.getProcedureCall();
				final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);

				result.addCalls(callsThrough);
				result.addCall(lastCall);
			}

			inputProcedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OnKey addOnKey(final SortOnKeyClauseContext ctx) {
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
				result.addKeyCall(keyCall);
			}

			result.setOnKeyType(type);

			onKeys.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OutputProcedure addOutputProcedure(final SortOutputProcedurePhraseContext ctx) {
		OutputProcedure result = (OutputProcedure) getASGElement(ctx);

		if (result == null) {
			result = new OutputProcedureImpl(programUnit, ctx);

			// procedure
			final Call firstCall = createCall(ctx.procedureName());
			result.setProcedureCall(firstCall);
			result.addCall(firstCall);

			// through
			if (ctx.sortOutputThrough() != null) {
				result.addOutputThrough(ctx.sortOutputThrough());

				final OutputThrough outputThrough = result.getOutputThrough();
				final Call lastCall = outputThrough.getProcedureCall();
				final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);

				result.addCalls(callsThrough);
				result.addCall(lastCall);
			}

			outputProcedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingPhrase addUsingPhrase(final SortUsingContext ctx) {
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
	public CollatingSequence getCollatingSequence() {
		return collatingSequence;
	}

	@Override
	public Duplicates getDuplicates() {
		return duplicates;
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
	public InputProcedure getInputProcedure() {
		return inputProcedure;
	}

	@Override
	public List<OnKey> getOnKeys() {
		return onKeys;
	}

	@Override
	public OutputProcedure getOutputProcedure() {
		return outputProcedure;
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
