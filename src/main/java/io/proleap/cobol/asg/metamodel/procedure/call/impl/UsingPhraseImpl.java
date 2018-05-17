/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.CallUsingParameterContext;
import io.proleap.cobol.CobolParser.CallUsingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingParameter;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingPhrase;

public class UsingPhraseImpl extends CobolDivisionElementImpl implements UsingPhrase {

	protected final CallUsingPhraseContext ctx;

	protected List<UsingParameter> usingParameters = new ArrayList<UsingParameter>();

	public UsingPhraseImpl(final ProgramUnit programUnit, final CallUsingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public UsingParameter addUsingParameter(final CallUsingParameterContext ctx) {
		UsingParameter result = (UsingParameter) getASGElement(ctx);

		if (result == null) {
			result = new UsingParameterImpl(programUnit, ctx);

			final UsingParameter.ParameterType type;

			// using call by reference
			if (ctx.callByReferencePhrase() != null) {
				result.addByReferencePhrase(ctx.callByReferencePhrase());
				type = UsingParameter.ParameterType.REFERENCE;
			}
			// using call by value
			else if (ctx.callByValuePhrase() != null) {
				result.addByValuePhrase(ctx.callByValuePhrase());
				type = UsingParameter.ParameterType.VALUE;
			}
			// using call by content
			else if (ctx.callByContentPhrase() != null) {
				result.addByContentPhrase(ctx.callByContentPhrase());
				type = UsingParameter.ParameterType.CONTENT;
			} else {
				type = null;
			}

			result.setParameterType(type);

			usingParameters.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<UsingParameter> getUsingParameters() {
		return usingParameters;
	}
}
