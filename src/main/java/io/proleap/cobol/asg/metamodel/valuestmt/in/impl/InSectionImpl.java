/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.in.impl;

import io.proleap.cobol.Cobol85Parser.InSectionContext;
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
