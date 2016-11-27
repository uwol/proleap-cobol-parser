/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubtractGivingContext;
import io.proleap.cobol.Cobol85Parser.SubtractMinuendGivingContext;
import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

public interface SubtractFromGiving extends Statement {

	Giving addGiving(SubtractGivingContext ctx);

	MinuendGiving addMinuend(SubtractMinuendGivingContext ctx);

	Subtrahend addSubtrahend(SubtractSubtrahendContext ctx);

	List<Giving> getGivings();

	MinuendGiving getMinuend();

	List<Subtrahend> getSubtrahends();

}
