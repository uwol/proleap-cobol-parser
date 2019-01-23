/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

/**
 * Transfers data from date or time register.
 */
public interface AcceptFromDateStatement extends CobolDivisionElement {

	enum DateType {
		DATE, DATE_YYYYMMDD, DAY, DAY_YYYYMMDD, MMDDYYYY, TIME, TIMER, TODAYS_DATE, TODAYS_DATE_MMDDYYYY, TODAYS_NAME,
		YEAR, YYYYDDD, YYYYMMDD
	}

	DateType getDateType();

	void setDateType(DateType dateType);
}
