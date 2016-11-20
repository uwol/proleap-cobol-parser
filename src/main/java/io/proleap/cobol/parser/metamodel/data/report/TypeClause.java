/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface TypeClause extends CobolDivisionElement {

	enum Type {
		ControlFooting, ControlHeading, Detail, PageFooting, PageHeading, ReportFooting, ReportHeading
	}

	ValueStmt getDataValueStmt();

	Type getType();

	void setDataValueStmt(ValueStmt dataValueStmt);

	void setType(Type type);
}
