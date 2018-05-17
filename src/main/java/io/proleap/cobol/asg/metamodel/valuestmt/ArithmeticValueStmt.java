/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import java.util.List;

import io.proleap.cobol.CobolParser.MultDivsContext;
import io.proleap.cobol.CobolParser.PlusMinusContext;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PlusMinus;

public interface ArithmeticValueStmt extends ValueStmt {

	MultDivs addMultDivs(MultDivsContext ctx);

	PlusMinus addPlusMinus(PlusMinusContext ctx);

	MultDivs getMultDivs();

	List<PlusMinus> getPlusMinus();
}
