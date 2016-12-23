/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;

public class ProcedureCallImpl extends CallImpl implements ProcedureCall {

	protected final CallType callType = CallType.PROCEDURE_CALL;

	protected Paragraph paragraph;

	public ProcedureCallImpl(final String name, final Paragraph paragraph, final ProgramUnit programUnit,
			final ParseTree ctx) {
		super(name, programUnit, ctx);

		this.paragraph = paragraph;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public Paragraph getParagraph() {
		return paragraph;
	}

	@Override
	public String toString() {
		return super.toString() + ", paragraph=[" + paragraph + "]";
	}
}
