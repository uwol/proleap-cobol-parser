/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.in.impl;

import io.proleap.cobol.CobolParser.InSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InSection;

public class InSectionImpl extends CobolDivisionElementImpl implements InSection {

	protected InSectionContext ctx;

	protected Call sectionCall;

	public InSectionImpl(final ProgramUnit programUnit, final InSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSectionCall() {
		return sectionCall;
	}

	@Override
	public void setSectionCall(final Call sectionCall) {
		this.sectionCall = sectionCall;
	}

}
