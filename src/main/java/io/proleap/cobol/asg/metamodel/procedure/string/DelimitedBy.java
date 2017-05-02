/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface DelimitedBy extends CobolDivisionElement {

	enum Type {
		CHARACTERS, SIZE
	}

	ValueStmt getCharactersValueStmt();

	Type getType();

	void setCharactersValueStmt(ValueStmt charactersValueStmt);

	void setType(Type type);
}
