/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.alter;

import java.util.List;

import io.proleap.cobol.CobolParser.AlterProceedToContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Changes the transfer point specified in a GO TO statement.
 */
public interface AlterStatement extends Statement {

	ProceedTo addProceedTo(AlterProceedToContext ctx);

	List<ProceedTo> getProceedTos();
}
