/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.CobolParser.EvaluateWhenOtherContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenOther;

public class WhenOtherImpl extends ScopeImpl implements WhenOther {

	protected final EvaluateWhenOtherContext ctx;

	public WhenOtherImpl(final ProgramUnit programUnit, final EvaluateWhenOtherContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
