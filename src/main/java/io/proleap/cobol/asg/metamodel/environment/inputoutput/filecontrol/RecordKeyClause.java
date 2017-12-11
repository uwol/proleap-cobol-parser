/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface RecordKeyClause extends CobolDivisionElement {

	PasswordClause getPasswordClause();

	Call getRecordKeyCall();

	void setPasswordClause(PasswordClause passwordClause);

	void setRecordKeyCall(Call recordKeyCall);

}
