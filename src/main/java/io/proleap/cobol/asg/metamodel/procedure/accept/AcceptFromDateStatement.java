/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

/**
 * Transfers data from date or time register.
 */
public interface AcceptFromDateStatement extends CobolDivisionElement {

	enum DateType {
		DATE, DATE_YYYYMMDD, DAY, DAY_YYYYMMDD, TIME, TIMER, TODAYS_DATE, TODAYS_DATE_MMDDYYYY, TODAYS_NAME, YEAR, YYYYDDD, YYYYMMDD
	}

	DateType getDateType();

	void setDateType(DateType dateType);
}
