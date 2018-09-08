/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open;

import java.util.List;

import io.proleap.cobol.CobolParser.OpenInputContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface InputPhrase extends CobolDivisionElement {

	Input addOpenInput(OpenInputContext ctx);

	List<Input> getInputs();
}
