/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.skip;

import io.proleap.cobol.asg.metamodel.procedure.Statement;

public interface SkipStatement extends Statement {

	enum SkipType {
		SKIP1, SKIP2, SKIP3
	}

	SkipType getSkipType();

	void setSkipType(SkipType skipType);
}
