/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsageClause extends CobolDivisionElement {

	enum UsageClauseType {
		DISPLAY, DISPLAY_1
	}

	UsageClauseType getUsageClauseType();

	void setUsageClauseType(UsageClauseType usageClauseType);
}
