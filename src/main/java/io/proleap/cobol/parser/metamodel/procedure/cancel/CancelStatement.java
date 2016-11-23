/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.cancel;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CancelCallContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Ensures that the referenced subprogram is entered in initial state next time
 * it is called.
 */
public interface CancelStatement extends Statement {

	CancelCall addCancelCall(CancelCallContext ctx);

	List<CancelCall> getCancelCalls();

}
