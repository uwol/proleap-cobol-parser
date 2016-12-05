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

	enum Type {
		Reel, Unit
	}

	enum WithType {
		Lock, NoRewind
	}

	Type getType();

	WithType getWithType();

	boolean isForRemovel();

	void setForRemoval(boolean forRemoval);

	void setType(Type type);

	void setWithType(WithType withType);
}
