/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.OpenExtendStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenIOStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenInputContext;
import io.proleap.cobol.Cobol85Parser.OpenInputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.ExtendPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.InputOutputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.InputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.OutputPhrase;

public class OpenStatementImpl extends StatementImpl implements OpenStatement {

	protected final OpenStatementContext ctx;

	protected List<ExtendPhrase> extendPhrases = new ArrayList<ExtendPhrase>();

	protected List<InputOutputPhrase> inputOutputPhrases = new ArrayList<InputOutputPhrase>();

	protected List<InputPhrase> inputPhrases = new ArrayList<InputPhrase>();

	protected List<OutputPhrase> outputPhrases = new ArrayList<OutputPhrase>();

	protected final StatementType statementType = StatementTypeEnum.OPEN;

	public OpenStatementImpl(final ProgramUnit programUnit, final Scope scope, final OpenStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ExtendPhrase addExtendPhrase(final OpenExtendStatementContext ctx) {
		ExtendPhrase result = (ExtendPhrase) getASGElement(ctx);

		if (result == null) {
			result = new ExtendPhraseImpl(programUnit, ctx);

			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			extendPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InputOutputPhrase addInputOutputPhrase(final OpenIOStatementContext ctx) {
		InputOutputPhrase result = (InputOutputPhrase) getASGElement(ctx);

		if (result == null) {
			result = new InputOutputPhraseImpl(programUnit, ctx);

			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			inputOutputPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InputPhrase addInputPhrase(final OpenInputStatementContext ctx) {
		InputPhrase result = (InputPhrase) getASGElement(ctx);

		if (result == null) {
			result = new InputPhraseImpl(programUnit, ctx);

			for (final OpenInputContext openInputContext : ctx.openInput()) {
				result.addOpenInput(openInputContext);
			}

			inputPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OutputPhrase addOutputPhrase(final OpenOutputStatementContext ctx) {
		OutputPhrase result = (OutputPhrase) getASGElement(ctx);

		if (result == null) {
			result = new OutputPhraseImpl(programUnit, ctx);

			for (final OpenOutputContext openOutputContext : ctx.openOutput()) {
				result.addOpenOutput(openOutputContext);
			}

			outputPhrases.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ExtendPhrase> getExtendPhrases() {
		return extendPhrases;
	}

	@Override
	public List<InputOutputPhrase> getInputOutputPhrases() {
		return inputOutputPhrases;
	}

	@Override
	public List<InputPhrase> getInputPhrases() {
		return inputPhrases;
	}

	@Override
	public List<OutputPhrase> getOutputPhrases() {
		return outputPhrases;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
