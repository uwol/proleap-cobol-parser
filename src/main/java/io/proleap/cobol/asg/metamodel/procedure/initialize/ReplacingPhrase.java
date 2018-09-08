/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize;

import java.util.List;

import io.proleap.cobol.CobolParser.InitializeReplacingByContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ReplacingPhrase extends CobolDivisionElement {

	By addBy(InitializeReplacingByContext ctx);

	List<By> getBys();
}
