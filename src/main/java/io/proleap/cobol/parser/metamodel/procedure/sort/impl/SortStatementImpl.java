/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.Cobol85Parser.SortCollatingSequencePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortDuplicatesPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortGivingContext;
import io.proleap.cobol.Cobol85Parser.SortGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortInputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SortOutputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortStatementContext;
import io.proleap.cobol.Cobol85Parser.SortUsingContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.sort.CollatingSequence;
import io.proleap.cobol.parser.metamodel.procedure.sort.Duplicates;
import io.proleap.cobol.parser.metamodel.procedure.sort.Givings;
import io.proleap.cobol.parser.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.parser.metamodel.procedure.sort.OnKey;
import io.proleap.cobol.parser.metamodel.procedure.sort.OutputProcedure;
import io.proleap.cobol.parser.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.parser.metamodel.procedure.sort.Using;

public class SortStatementImpl extends StatementImpl implements SortStatement {

	protected CollatingSequence collatingSequence;

	protected final SortStatementContext ctx;

	protected Duplicates duplicates;

	protected Call fileCall;

	protected List<Givings> givings = new ArrayList<Givings>();

	protected InputProcedure inputProcedure;

	protected List<OnKey> onKeys = new ArrayList<OnKey>();

	protected OutputProcedure outputProcedure;

	protected List<Using> usings = new ArrayList<Using>();

	public SortStatementImpl(final ProgramUnit programUnit, final SortStatementContext ctx) {
		super(programUnit, ctx);

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
	public Givings addGiving(final SortGivingPhraseContext ctx) {
		Givings result = (Givings) getASGElement(ctx);

		if (result == null) {
			result = new GivingsImpl(programUnit, ctx);

			// givings
			for (final SortGivingContext sortGivingContext : ctx.sortGiving()) {
				result.addGiving(sortGivingContext);
			}

			givings.add(result);
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
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			// through
			if (ctx.sortInputThrough() != null) {
				result.addInputThrough(ctx.sortInputThrough());
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
			final OnKey.Type type;

			if (ctx.ASCENDING() != null) {
				type = OnKey.Type.Ascending;
			} else if (ctx.DESCENDING() != null) {
				type = OnKey.Type.Descending;
			} else {
				type = null;
			}

			// key call
			for (final QualifiedDataNameContext qualifiedDataNameContext : ctx.qualifiedDataName()) {
				final Call keyCall = createCall(qualifiedDataNameContext);
				result.adKeyCall(keyCall);
			}

			result.setType(type);

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
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			// through
			if (ctx.sortOutputThrough() != null) {
				result.addOutputThrough(ctx.sortOutputThrough());
			}

			outputProcedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Using addUsing(final SortUsingContext ctx) {
		Using result = (Using) getASGElement(ctx);

		if (result == null) {
			result = new UsingImpl(programUnit, ctx);

			// file calls
			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			usings.add(result);
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
	public List<Givings> getGivings() {
		return givings;
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
	public List<Using> getUsings() {
		return usings;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
