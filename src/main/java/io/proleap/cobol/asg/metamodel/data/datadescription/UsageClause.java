/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsageClause extends CobolDivisionElement {

	enum UsageClauseType {
		BINARY, BINARY_EXTENDED, BINARY_TRUNCATED, BIT, COMP, COMP_1, COMP_2, COMP_3, COMP_4, COMP_5, CONTROL_POINT, DATE, DISPLAY, DISPLAY_1, DOUBLE, EVENT, FUNCTION_POINTER, INDEX, KANJI, LOCK, NATIONAL, PACKED_DECIMAL, POINTER, PROCEDURE_POINTER, REAL, SQL, TASK
	}

	UsageClauseType getUsageClauseType();

	void setUsageClauseType(UsageClauseType usageClauseType);
}
