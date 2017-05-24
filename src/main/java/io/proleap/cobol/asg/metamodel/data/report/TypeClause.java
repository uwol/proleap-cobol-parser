/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface TypeClause extends CobolDivisionElement {

	enum TypeClauseType {
		CONTROL_FOOTING, CONTROL_HEADING, DETAIL, PAGE_FOOTING, PAGE_HEADING, REPORT_FOOTING, REPORT_HEADING
	}

	Call getDataCall();

	TypeClauseType getTypeClauseType();

	void setDataCall(Call dataCall);

	void setTypeClauseType(TypeClauseType typeClauseType);
}
