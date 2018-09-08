/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply;

import java.util.List;

import io.proleap.cobol.CobolParser.MultiplyGivingOperandContext;
import io.proleap.cobol.CobolParser.MultiplyGivingResultContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface GivingPhrase extends CobolDivisionElement {

	GivingOperand addGivingOperand(MultiplyGivingOperandContext ctx);

	GivingResult addGivingResult(MultiplyGivingResultContext ctx);

	GivingOperand getGivingOperand();

	List<GivingResult> getGivingResults();
}
