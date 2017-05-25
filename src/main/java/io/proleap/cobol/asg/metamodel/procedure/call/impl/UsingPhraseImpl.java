/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallUsingParameterContext;
import io.proleap.cobol.Cobol85Parser.CallUsingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.Parameter;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingPhrase;

public class UsingPhraseImpl extends CobolDivisionElementImpl implements UsingPhrase {

	protected final CallUsingPhraseContext ctx;

	protected List<Parameter> parameters = new ArrayList<Parameter>();

	public UsingPhraseImpl(final ProgramUnit programUnit, final CallUsingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Parameter addParameter(final CallUsingParameterContext ctx) {
		Parameter result = (Parameter) getASGElement(ctx);

		if (result == null) {
			result = new ParameterImpl(programUnit, ctx);

			final Parameter.ParameterType type;

			// using call by reference
			if (ctx.callByReferencePhrase() != null) {
				result.addCallByReference(ctx.callByReferencePhrase());
				type = Parameter.ParameterType.REFERENCE;
			}
			// using call by value
			else if (ctx.callByValuePhrase() != null) {
				result.addCallByValue(ctx.callByValuePhrase());
				type = Parameter.ParameterType.VALUE;
			}
			// using call by content
			else if (ctx.callByContentPhrase() != null) {
				result.addCallByContent(ctx.callByContentPhrase());
				type = Parameter.ParameterType.CONTENT;
			} else {
				type = null;
			}

			result.setParameterType(type);

			parameters.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Parameter> getParameters() {
		return parameters;
	}

}
