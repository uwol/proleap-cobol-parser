/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.Cobol85Parser.CallByContentPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.Cobol85Parser.CallByReferencePhraseContext;
import io.proleap.cobol.Cobol85Parser.CallByValueContext;
import io.proleap.cobol.Cobol85Parser.CallByValuePhraseContext;
import io.proleap.cobol.Cobol85Parser.CallUsingParameterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByValue;
import io.proleap.cobol.asg.metamodel.procedure.call.Parameter;

public class ParameterImpl extends CobolDivisionElementImpl implements Parameter {

	protected CallByContent callByContent;

	protected CallByReference callByReference;

	protected CallByValue callByValue;

	protected final CallUsingParameterContext ctx;

	protected ParameterType parameterType;

	public ParameterImpl(final ProgramUnit programUnit, final CallUsingParameterContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CallByContent addCallByContent(final CallByContentPhraseContext ctx) {
		CallByContent result = (CallByContent) getASGElement(ctx);

		if (result == null) {
			result = new CallByContentImpl(programUnit, ctx);

			for (final CallByContentContext callByContentContext : ctx.callByContent()) {
				result.addByContent(callByContentContext);
			}

			callByContent = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallByReference addCallByReference(final CallByReferencePhraseContext ctx) {
		CallByReference result = (CallByReference) getASGElement(ctx);

		if (result == null) {
			result = new CallByReferenceImpl(programUnit, ctx);

			for (final CallByReferenceContext callByReferenceContext : ctx.callByReference()) {
				result.addByReference(callByReferenceContext);
			}

			callByReference = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallByValue addCallByValue(final CallByValuePhraseContext ctx) {
		CallByValue result = (CallByValue) getASGElement(ctx);

		if (result == null) {
			result = new CallByValueImpl(programUnit, ctx);

			for (final CallByValueContext callByValueContext : ctx.callByValue()) {
				result.addValueStmt(callByValueContext);
			}

			callByValue = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallByContent getCallByContent() {
		return callByContent;
	}

	@Override
	public CallByReference getCallByReference() {
		return callByReference;
	}

	@Override
	public CallByValue getCallByValue() {
		return callByValue;
	}

	@Override
	public ParameterType getParameterType() {
		return parameterType;
	}

	@Override
	public void setParameterType(final ParameterType parameterType) {
		this.parameterType = parameterType;
	}
}
