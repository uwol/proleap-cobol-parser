/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract;

import java.util.List;

import io.proleap.cobol.CobolParser.SubtractGivingContext;
import io.proleap.cobol.CobolParser.SubtractMinuendGivingContext;
import io.proleap.cobol.CobolParser.SubtractSubtrahendContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SubtractFromGivingStatement extends CobolDivisionElement {

	Giving addGiving(SubtractGivingContext ctx);

	MinuendGiving addMinuend(SubtractMinuendGivingContext ctx);

	Subtrahend addSubtrahend(SubtractSubtrahendContext ctx);

	List<Giving> getGivings();

	MinuendGiving getMinuend();

	List<Subtrahend> getSubtrahends();
}
