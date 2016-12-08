/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;

public interface ColumnNumberClause extends CobolDivisionElement {

	enum Type {
		Equal, Minus, Plus
	}

	IntegerLiteral getIntegerLiteral();

	Type getType();

	void setIntegerLiteral(IntegerLiteral integerLiteral);

	void setType(Type type);

}
