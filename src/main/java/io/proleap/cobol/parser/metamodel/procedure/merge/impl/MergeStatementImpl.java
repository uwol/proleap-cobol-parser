/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.merge.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.MergeCollatingSequencePhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeGivingContext;
import io.proleap.cobol.Cobol85Parser.MergeGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MergeOutputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeStatementContext;
import io.proleap.cobol.Cobol85Parser.MergeUsingContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.merge.CollatingSequence;
import io.proleap.cobol.parser.metamodel.procedure.merge.Givings;
import io.proleap.cobol.parser.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.parser.metamodel.procedure.merge.OnKey;
import io.proleap.cobol.parser.metamodel.procedure.merge.OutputProcedure;
import io.proleap.cobol.parser.metamodel.procedure.merge.Using;

public class MergeStatementImpl extends StatementImpl implements MergeStatement {

	protected CollatingSequence collatingSequence;

	protected final MergeStatementContext ctx;

	protected Call fileCall;

	protected List<Givings> givings = new ArrayList<Givings>();

	protected List<OnKey> onKeys = new ArrayList<OnKey>();

	protected OutputProcedure outputProcedure;

	protected List<Using> usings = new ArrayList<Using>();

	public MergeStatementImpl(final ProgramUnit programUnit, final Scope scope, final MergeStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CollatingSequence addCollatingSequence(final MergeCollatingSequencePhraseContext ctx) {
		CollatingSequence result = (CollatingSequence) getASGElement(ctx);

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

			collatingSequence = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Givings addGiving(final MergeGivingPhraseContext ctx) {
		Givings result = (Givings) getASGElement(ctx);

		if (result == null) {
			result = new GivingsImpl(programUnit, ctx);

			// givings
			for (final MergeGivingContext mergeGivingContext : ctx.mergeGiving()) {
				result.addGiving(mergeGivingContext);
			}

			givings.add(result);
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
	public OutputProcedure addOutputProcedure(final MergeOutputProcedurePhraseContext ctx) {
		OutputProcedure result = (OutputProcedure) getASGElement(ctx);

		if (result == null) {
			result = new OutputProcedureImpl(programUnit, ctx);

			// procedure
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			// through
			if (ctx.mergeOutputThrough() != null) {
				result.addOutputThrough(ctx.mergeOutputThrough());
			}

			outputProcedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Using addUsing(final MergeUsingContext ctx) {
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
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public List<Givings> getGivings() {
		return givings;
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
