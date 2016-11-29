/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.Cobol85Parser.EvaluateWhenOtherContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.ScopeImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.WhenOther;

public class WhenOtherImpl extends ScopeImpl implements WhenOther {

	protected final EvaluateWhenOtherContext ctx;

	public WhenOtherImpl(final ProgramUnit programUnit, final EvaluateWhenOtherContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
