/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface SignClause extends CobolDivisionElement {

	enum Type {
		Leading, Trailing
	}

	Type getType();

	Boolean isSeparate();

	void setSeparate(Boolean separate);

	void setType(Type type);

}
