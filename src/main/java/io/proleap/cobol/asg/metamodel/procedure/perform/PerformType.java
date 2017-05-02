/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.Cobol85Parser.PerformTimesContext;
import io.proleap.cobol.Cobol85Parser.PerformUntilContext;
import io.proleap.cobol.Cobol85Parser.PerformVaryingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface PerformType extends CobolDivisionElement {

	enum Type {
		TIMES, UNTIL, VARYING
	}

	Times addTimes(PerformTimesContext ctx);

	Until addUntil(PerformUntilContext ctx);

	Varying addVarying(PerformVaryingContext ctx);

	Times getTimes();

	Type getType();

	Until getUntil();

	Varying getVarying();

	void setType(Type type);
}
