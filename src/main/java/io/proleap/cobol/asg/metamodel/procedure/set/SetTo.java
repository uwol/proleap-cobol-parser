/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.CobolParser.SetToContext;
import io.proleap.cobol.CobolParser.SetToValueContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SetTo extends CobolDivisionElement {

	To addTo(SetToContext ctx);

	Value addValue(SetToValueContext ctx);

	List<To> getTos();

	List<Value> getValues();
}
