/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.release;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Transfers records to the initial phase of a sort operation and writes records
 * to a sort file or a merge file.
 */
public interface ReleaseStatement extends Statement {

	Call getContentCall();

	Call getRecordCall();

	void setContentCall(Call contentCall);

	void setRecordCall(Call recordCall);
}
