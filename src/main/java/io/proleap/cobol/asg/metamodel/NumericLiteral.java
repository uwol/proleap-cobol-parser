/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.Cobol85Parser.NumericLiteralContext;
import io.proleap.cobol.asg.metamodel.type.TypedElement;

public interface NumericLiteral extends CobolDivisionElement, TypedElement {

	enum NumericLiteralType {
		DOUBLE, INTEGER
	}

	@Override
	NumericLiteralContext getCtx();

	Double getDoubleValue();

	Integer getIntegerValue();

	NumericLiteralType getNumericLiteralType();

	Object getValue();

	void setDoubleValue(Double doubleValue);

	void setIntegerValue(Integer integerValue);

	void setNumericLiteralType(NumericLiteralType numericLiteralType);
}
