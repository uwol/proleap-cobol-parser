/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import io.proleap.cobol.CobolParser.UseAfterOnContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UseAfterStatement extends CobolDivisionElement {

	AfterOn addAfterOn(UseAfterOnContext ctx);

	AfterOn getAfterOn();

	boolean isGlobal();

	void setGlobal(boolean global);
}
