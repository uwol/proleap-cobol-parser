/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SetToContext;
import io.proleap.cobol.Cobol85Parser.SetToValueContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SetTo extends CobolDivisionElement {

	To addTo(SetToContext ctx);

	Value addValue(SetToValueContext ctx);

	List<To> getTos();

	List<Value> getValues();

}
