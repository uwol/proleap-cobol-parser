/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.set;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SetByValueContext;
import io.proleap.cobol.Cobol85Parser.SetToContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface SetBy extends CobolDivisionElement {

	enum Type {
		Down, Up
	}

	By addBy(SetByValueContext ctx);

	To addTo(SetToContext ctx);

	By getBy();

	List<To> getTos();

	Type getType();

	void setType(Type type);
}
