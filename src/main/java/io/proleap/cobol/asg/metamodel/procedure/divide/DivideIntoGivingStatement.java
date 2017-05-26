/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface DivideIntoGivingStatement extends CobolDivisionElement {

	Giving addGiving(DivideGivingContext ctx);

	List<Giving> getGivings();

}
