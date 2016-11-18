/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface UsingClause extends CobolDivisionElement {

	enum Type {
		Convention, Language
	}

	ValueStmt getOfValueStmt();

	Type getType();

	void setOfValueStmt(ValueStmt ofValueStmt);

	void setType(Type type);
}
