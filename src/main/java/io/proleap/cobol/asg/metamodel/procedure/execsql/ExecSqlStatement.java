/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.execsql;

import io.proleap.cobol.asg.metamodel.procedure.Statement;

public interface ExecSqlStatement extends Statement {

	String getExecSqlText();

	void setExecSqlText(String execSqlText);

}
