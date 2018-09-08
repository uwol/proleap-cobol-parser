/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import java.util.List;

import io.proleap.cobol.CobolParser.UnstringIntoContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface IntoPhrase extends CobolDivisionElement {

	Into addInto(UnstringIntoContext ctx);

	List<Into> getIntos();
}
