/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move;

import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Transfers data from one storage area to another.
 */
public interface MoveToStatement extends Statement {

	void addReceivingAreaCall(Call receivingAreaCall);

	ValueStmt addSendingAreaValueStmt(MoveToStatementSendingAreaContext ctx);

}
