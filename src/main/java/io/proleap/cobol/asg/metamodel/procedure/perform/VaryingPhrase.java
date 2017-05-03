/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.Cobol85Parser.PerformByContext;
import io.proleap.cobol.Cobol85Parser.PerformFromContext;
import io.proleap.cobol.Cobol85Parser.PerformUntilContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface VaryingPhrase extends CobolDivisionElement {

	By addBy(PerformByContext ctx);

	From addFrom(PerformFromContext ctx);

	Until addUntil(PerformUntilContext ctx);

	By getBy();

	From getFrom();

	Until getUntil();

	ValueStmt getVaryingValueStmt();

	void setVaryingValueStmt(ValueStmt varyingValueStmt);
}
