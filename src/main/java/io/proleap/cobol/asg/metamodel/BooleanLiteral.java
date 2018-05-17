/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.CobolParser.BooleanLiteralContext;

public interface BooleanLiteral extends CobolDivisionElement {

	@Override
	BooleanLiteralContext getCtx();

	Boolean getValue();
}
