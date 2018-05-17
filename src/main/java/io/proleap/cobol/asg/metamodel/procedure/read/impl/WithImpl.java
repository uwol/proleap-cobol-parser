/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read.impl;

import io.proleap.cobol.CobolParser.ReadWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.read.With;

public class WithImpl extends CobolDivisionElementImpl implements With {

	protected final ReadWithContext ctx;

	protected WithType withType;

	public WithImpl(final ProgramUnit programUnit, final ReadWithContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public WithType getWithType() {
		return withType;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
