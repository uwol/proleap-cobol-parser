/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.CobolParser.PerformTypeContext;
import io.proleap.cobol.asg.metamodel.Scope;

public interface PerformInlineStatement extends Scope {

	PerformType addPerformType(PerformTypeContext ctx);

	PerformType getPerformType();
}
