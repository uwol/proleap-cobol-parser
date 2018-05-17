/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import io.proleap.cobol.CobolParser.PerformTimesContext;
import io.proleap.cobol.CobolParser.PerformUntilContext;
import io.proleap.cobol.CobolParser.PerformVaryingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface PerformType extends CobolDivisionElement {

	enum PerformTypeType {
		TIMES, UNTIL, VARYING
	}

	Times addTimes(PerformTimesContext ctx);

	Until addUntil(PerformUntilContext ctx);

	Varying addVarying(PerformVaryingContext ctx);

	PerformTypeType getPerformTypeType();

	Times getTimes();

	Until getUntil();

	Varying getVarying();

	void setPerformTypeType(PerformTypeType performTypeType);
}
