/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface CloseReelUnitStatement extends CobolDivisionElement {

	enum CloseReelUnitType {
		REEL, UNIT
	}

	enum WithType {
		LOCK, NO_REWIND
	}

	CloseReelUnitType getCloseReelUnitType();

	WithType getWithType();

	boolean isForRemovel();

	void setCloseReelUnitType(CloseReelUnitType closeReelUnitType);

	void setForRemoval(boolean forRemoval);

	void setWithType(WithType withType);
}
