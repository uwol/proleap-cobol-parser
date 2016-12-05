/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.alter;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlterProceedToContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Changes the transfer point specified in a GO TO statement.
 */
public interface AlterStatement extends Statement {

	AlterProceedTo addAlterProceedTo(AlterProceedToContext ctx);

	List<AlterProceedTo> getAlterProceedTos();
}
