/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.Cobol85Parser.BooleanLiteralContext;
import io.proleap.cobol.asg.metamodel.type.TypedElement;

public interface BooleanLiteral extends CobolDivisionElement, TypedElement {

	@Override
	BooleanLiteralContext getCtx();

	Boolean getValue();
}
