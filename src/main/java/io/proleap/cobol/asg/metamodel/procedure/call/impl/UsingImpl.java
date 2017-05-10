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
import io.proleap.cobol.asg.metamodel.procedure.call.Using;

public class UsingImpl extends CobolDivisionElementImpl implements Using {

	protected final CallUsingPhraseContext ctx;

	protected List<Parameter> parameters = new ArrayList<Parameter>();

	public UsingImpl(final ProgramUnit programUnit, final CallUsingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Parameter addParameter(final CallUsingParameterContext ctx) {
		Parameter result = (Parameter) getASGElement(ctx);

		if (result == null) {
			result = new ParameterImpl(programUnit, ctx);

			final Parameter.Type type;

			// using call by reference
			if (ctx.callByReferencePhrase() != null) {
				result.addCallByReference(ctx.callByReferencePhrase());
				type = Parameter.Type.REFERENCE;
			}
			// using call by value
			else if (ctx.callByValuePhrase() != null) {
				result.addCallByValue(ctx.callByValuePhrase());
				type = Parameter.Type.VALUE;
			}
			// using call by content
			else if (ctx.callByContentPhrase() != null) {
				result.addCallByContent(ctx.callByContentPhrase());
				type = Parameter.Type.CONTENT;
			} else {
				type = null;
			}

			result.setType(type);

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
