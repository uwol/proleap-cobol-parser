/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge;

import java.util.List;

import io.proleap.cobol.CobolParser.MergeGivingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface GivingPhrase extends CobolDivisionElement {

	Giving addGiving(MergeGivingContext ctx);

	List<Giving> getGivings();
}
