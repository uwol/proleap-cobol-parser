/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform;

import java.util.List;

import io.proleap.cobol.CobolParser.PerformAfterContext;
import io.proleap.cobol.CobolParser.PerformVaryingPhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface VaryingClause extends CobolDivisionElement {

	After addAfter(PerformAfterContext ctx);

	VaryingPhrase addVaryingPhrase(PerformVaryingPhraseContext ctx);

	List<After> getAfters();

	VaryingPhrase getVaryingPhrase();
}
