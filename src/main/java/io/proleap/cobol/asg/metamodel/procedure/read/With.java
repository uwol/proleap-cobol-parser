/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface With extends CobolDivisionElement {

	enum WithType {
		KEPT_LOCK, NO_LOCK, WAIT
	}

	WithType getWithType();

	void setWithType(WithType withType);
}
