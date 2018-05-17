/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.declaratives.impl;

import io.proleap.cobol.CobolParser.ProcedureSectionHeaderContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.SectionHeader;

public class SectionHeaderImpl extends CobolDivisionElementImpl implements SectionHeader {

	protected final ProcedureSectionHeaderContext ctx;

	public SectionHeaderImpl(final ProgramUnit programUnit, final ProcedureSectionHeaderContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
