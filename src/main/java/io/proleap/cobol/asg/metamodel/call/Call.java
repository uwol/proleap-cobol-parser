/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.NamedElement;

public interface Call extends CobolDivisionElement, NamedElement {

	public enum CallType {
		COMMUNICATION_DESCRIPTION_ENTRY_CALL, DATA_DESCRIPTION_ENTRY_CALL, ENVIRONMENT_CALL, FILE_DESCRIPTION_ENTRY_CALL, MNEMONIC_CALL, PROCEDURE_CALL, REPORT_DESCRIPTION_CALL, REPORT_DESCRIPTION_ENTRY_CALL, SCREEN_DESCRIPTION_ENTRY_CALL, UNDEFINED_CALL, VARIABLE_CALL;
	}

	CallType getCallType();

	Call unwrap();
}
