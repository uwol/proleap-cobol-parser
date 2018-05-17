/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.ProcedureDivisionUsingClauseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionUsingParameterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.UsingClause;
import io.proleap.cobol.asg.metamodel.procedure.UsingParameter;
import io.proleap.cobol.asg.metamodel.procedure.UsingParameter.UsingParameterType;

public class UsingClauseImpl extends CobolDivisionElementImpl implements UsingClause {

	private final static Logger LOG = LoggerFactory.getLogger(UsingClauseImpl.class);

	protected final ProcedureDivisionUsingClauseContext ctx;

	protected List<UsingParameter> usingParameters = new ArrayList<UsingParameter>();

	public UsingClauseImpl(final ProgramUnit programUnit, final ProcedureDivisionUsingClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public UsingParameter addUsingParameter(final ProcedureDivisionUsingParameterContext ctx) {
		UsingParameter result = (UsingParameter) getASGElement(ctx);

		if (result == null) {
			result = new UsingParameterImpl(programUnit, ctx);

			// parameter
			final UsingParameterType usingParameterType;

			if (ctx.procedureDivisionByReferencePhrase() != null) {
				result.addByReferencePhrase(ctx.procedureDivisionByReferencePhrase());
				usingParameterType = UsingParameterType.REFERENCE;
			} else if (ctx.procedureDivisionByValuePhrase() != null) {
				result.addByValuePhrase(ctx.procedureDivisionByValuePhrase());
				usingParameterType = UsingParameterType.VALUE;
			} else {
				LOG.warn("unknown using parameter at {}", ctx);
				usingParameterType = null;
			}

			result.setUsingParameterType(usingParameterType);

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
