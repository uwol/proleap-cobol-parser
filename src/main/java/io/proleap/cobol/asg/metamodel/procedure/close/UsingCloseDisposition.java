/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsingCloseDisposition extends CobolDivisionElement {

	enum Type {
		ABORT, ORDERLY
	}

	Type getType();

	void setType(Type type);
}
