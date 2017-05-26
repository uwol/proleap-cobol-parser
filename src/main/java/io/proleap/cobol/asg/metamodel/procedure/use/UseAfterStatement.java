/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import io.proleap.cobol.Cobol85Parser.UseAfterOnContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UseAfterStatement extends CobolDivisionElement {

	AfterOn addAfterOn(UseAfterOnContext ctx);

	AfterOn getAfterOn();

	boolean isGlobal();

	void setGlobal(boolean global);

}
