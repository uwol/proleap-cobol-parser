/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubtractMinuendContext;
import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface SubtractFrom extends CobolDivisionElement {

	Minuend addMinuend(SubtractMinuendContext ctx);

	Subtrahend addSubtrahend(SubtractSubtrahendContext ctx);

	List<Minuend> getMinuends();

	List<Subtrahend> getSubtrahends();

}
