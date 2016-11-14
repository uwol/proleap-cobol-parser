/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface AlternateRecordKeyClause extends CobolDivisionElement {

	PasswordClause getPasswordClause();

	ValueStmt getValueStmt();

	void setPasswordClause(PasswordClause passwordClause);

	void setValueStmt(ValueStmt valueStmt);

}
