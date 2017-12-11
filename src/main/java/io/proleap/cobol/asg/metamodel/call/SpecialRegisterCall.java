/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call;

public interface SpecialRegisterCall extends Call {

	public enum SpecialRegisterType {
		ADDRESS_OF, DATE, DAY, DAY_OF_WEEK, DEBUG_CONTENTS, DEBUG_ITEM, DEBUG_LINE, DEBUG_NAME, DEBUG_SUB_1, DEBUG_SUB_2, DEBUG_SUB_3, LENGTH_OF, LINAGE_COUNTER, LINE_COUNTER, PAGE_COUNTER, RETURN_CODE, SHIFT_IN, SHIFT_OUT, SORT_CONTROL, SORT_CORE_SIZE, SORT_FILE_SIZE, SORT_MESSAGE, SORT_MODE_SIZE, SORT_RETURN, TALLY, TIME, WHEN_COMPILED
	}

	Call getIdentifierCall();

	SpecialRegisterType getSpecialRegisterType();

	void setIdentifierCall(Call identifierCall);

}
