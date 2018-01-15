/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.SectionCall;
import io.proleap.cobol.asg.metamodel.procedure.Section;

public class SectionCallImpl extends CallImpl implements SectionCall {

	protected final CallType callType = CallType.SECTION_CALL;

	protected Section section;

	public SectionCallImpl(final String name, final Section section, final ProgramUnit programUnit,
			final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.section = section;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public Section getSection() {
		return section;
	}

	@Override
	public String toString() {
		return super.toString() + ", section=[" + section + "]";
	}
}
