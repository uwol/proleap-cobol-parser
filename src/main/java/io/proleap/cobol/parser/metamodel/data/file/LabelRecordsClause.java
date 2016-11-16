/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file;

import java.util.List;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface LabelRecordsClause extends CobolDivisionElement {

	enum Type {
		DataNames, Omitted, Standard
	}

	void addDataNameValueStmt(ValueStmt dataName);

	List<ValueStmt> getDataNames();

	Type getType();

	void setType(Type type);
}
