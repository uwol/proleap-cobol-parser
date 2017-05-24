/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface RecordDelimiterClause extends CobolDivisionElement {

	enum RecordDelimiterClauseType {
		ASSIGNMENT, IMPLICIT, STANDARD_1
	}

	RecordDelimiterClauseType getRecordDelimiterClauseType();

	ValueStmt getValueStmt();

	void setRecordDelimiterClauseType(RecordDelimiterClauseType recordDelimiterClauseType);

	void setValueStmt(ValueStmt valueStmt);

}
