/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface RecordKeyClause extends CobolDivisionElement {

	PasswordClause getPasswordClause();

	Call getRecordKeyCall();

	void setPasswordClause(PasswordClause passwordClause);

	void setRecordKeyCall(Call recordKeyCall);

}
