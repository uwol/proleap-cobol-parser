/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.CobolParser.SetByValueContext;
import io.proleap.cobol.CobolParser.SetToContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SetBy extends CobolDivisionElement {

	enum SetByType {
		DOWN, UP
	}

	By addBy(SetByValueContext ctx);

	To addTo(SetToContext ctx);

	By getBy();

	SetByType getSetByType();

	List<To> getTos();

	void setSetByType(SetByType setByType);
}
