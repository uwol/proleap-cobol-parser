/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.NamedElement;

public interface Call extends CobolDivisionElement, NamedElement {

	public enum CallType {
		COMMUNICATION_DESCRIPTION_ENTRY_CALL, DATA_DESCRIPTION_ENTRY_CALL, ENVIRONMENT_CALL, FILE_CONTROL_ENTRY_CALL, FUNCTION_CALL, INDEX_CALL, MNEMONIC_CALL, PROCEDURE_CALL, REPORT_DESCRIPTION_CALL, REPORT_DESCRIPTION_ENTRY_CALL, SCREEN_DESCRIPTION_ENTRY_CALL, SECTION_CALL, SPECIAL_REGISTER_CALL, TABLE_CALL, UNDEFINED_CALL;
	}

	CallType getCallType();

	Call unwrap();
}
