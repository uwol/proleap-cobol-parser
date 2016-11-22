/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

/**
 * Transfers data from date or time register.
 */
public interface AcceptFromDate extends CobolDivisionElement {

	enum DateType {
		Date, DateYyyyMmDd, Day, DayYyyyMmDd, Time, Timer, TodaysDate, TodaysDateMmDdYyyy, TodaysName, Year, YyyyDdd, YyyyMmDd
	}

	DateType getDateType();

	void setDateType(DateType dateType);
}
