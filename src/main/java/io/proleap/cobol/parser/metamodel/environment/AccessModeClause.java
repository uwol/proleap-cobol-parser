/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface AccessModeClause extends CobolDivisionElement {

	enum Mode {
		Dynamic, Exclusive, Random, Sequential
	}

	Mode getMode();

	void setMode(Mode mode);
}
