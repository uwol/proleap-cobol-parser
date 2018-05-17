/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.CobolParser.ProcedureDivisionByReferenceContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionByReferencePhraseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionByValueContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionByValuePhraseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionUsingParameterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ByReferencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.ByValuePhrase;
import io.proleap.cobol.asg.metamodel.procedure.UsingParameter;

public class UsingParameterImpl extends CobolDivisionElementImpl implements UsingParameter {

	protected ByReferencePhrase byReferencePhrase;

	protected ByValuePhrase byValuePhrase;

	protected final ProcedureDivisionUsingParameterContext ctx;

	protected UsingParameterType usingParameterType;

	public UsingParameterImpl(final ProgramUnit programUnit, final ProcedureDivisionUsingParameterContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReferencePhrase addByReferencePhrase(final ProcedureDivisionByReferencePhraseContext ctx) {
		ByReferencePhrase result = (ByReferencePhrase) getASGElement(ctx);

		if (result == null) {
			result = new ByReferencePhraseImpl(programUnit, ctx);

			for (final ProcedureDivisionByReferenceContext procedureDivisionByReferenceContext : ctx
					.procedureDivisionByReference()) {
				result.addByReference(procedureDivisionByReferenceContext);
			}

			byReferencePhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ByValuePhrase addByValuePhrase(final ProcedureDivisionByValuePhraseContext ctx) {
		ByValuePhrase result = (ByValuePhrase) getASGElement(ctx);

		if (result == null) {
			result = new ByValuePhraseImpl(programUnit, ctx);

			for (final ProcedureDivisionByValueContext procedureDivisionByValueContext : ctx
					.procedureDivisionByValue()) {
				result.addByValue(procedureDivisionByValueContext);
			}

			byValuePhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ByReferencePhrase getByReferencePhrase() {
		return byReferencePhrase;
	}

	@Override
	public ByValuePhrase getByValuePhrase() {
		return byValuePhrase;
	}

	@Override
	public UsingParameterType getUsingParameterType() {
		return usingParameterType;
	}

	@Override
	public void setUsingParameterType(final UsingParameterType usingParameterType) {
		this.usingParameterType = usingParameterType;
	}
}
