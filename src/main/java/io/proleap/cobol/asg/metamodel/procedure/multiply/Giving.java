/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultiplyGivingOperandContext;
import io.proleap.cobol.Cobol85Parser.MultiplyGivingResultContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Giving extends CobolDivisionElement {

	GivingOperand addOperand(MultiplyGivingOperandContext ctx);

	GivingResult addResult(MultiplyGivingResultContext ctx);

	GivingOperand getOperand();

	List<GivingResult> getResults();

}
