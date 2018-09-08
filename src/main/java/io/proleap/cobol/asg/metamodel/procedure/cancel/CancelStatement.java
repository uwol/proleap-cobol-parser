/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.cancel;

import java.util.List;

import io.proleap.cobol.CobolParser.CancelCallContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Ensures that the referenced subprogram is entered in initial state next time
 * it is called.
 */
public interface CancelStatement extends Statement {

	CancelCall addCancelCall(CancelCallContext ctx);

	List<CancelCall> getCancelCalls();
}
