/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.CobolParser.PerformByContext;
import io.proleap.cobol.CobolParser.PerformFromContext;
import io.proleap.cobol.CobolParser.PerformUntilContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface VaryingPhrase extends CobolDivisionElement {

	ByPhrase addByPhrase(PerformByContext ctx);

	FromPhrase addFromPhrase(PerformFromContext ctx);

	Until addUntil(PerformUntilContext ctx);

	ByPhrase getBy();

	FromPhrase getFrom();

	Until getUntil();

	ValueStmt getVaryingValueStmt();

	void setVaryingValueStmt(ValueStmt varyingValueStmt);
}
