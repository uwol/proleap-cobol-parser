/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send;

import io.proleap.cobol.CobolParser.SendStatementAsyncContext;
import io.proleap.cobol.CobolParser.SendStatementSyncContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Sends data to another program.
 */
public interface SendStatement extends Statement {

	enum SendType {
		ASYNC, SYNC
	}

	Async addAsync(SendStatementAsyncContext ctx);

	Sync addSync(SendStatementSyncContext ctx);

	Async getAsync();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	SendType getSendType();

	Sync getSync();

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);

	void setSendType(SendType sendType);
}
