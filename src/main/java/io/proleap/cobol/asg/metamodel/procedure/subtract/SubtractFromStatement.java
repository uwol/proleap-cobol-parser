/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract;

import java.util.List;

import io.proleap.cobol.CobolParser.SubtractMinuendContext;
import io.proleap.cobol.CobolParser.SubtractSubtrahendContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SubtractFromStatement extends CobolDivisionElement {

	Minuend addMinuend(SubtractMinuendContext ctx);

	Subtrahend addSubtrahend(SubtractSubtrahendContext ctx);

	List<Minuend> getMinuends();

	List<Subtrahend> getSubtrahends();
}
