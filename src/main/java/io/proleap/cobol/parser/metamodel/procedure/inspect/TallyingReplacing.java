/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectForContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingPhraseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface TallyingReplacing extends CobolDivisionElement {

	For addFor(InspectForContext ctx);

	Replacing addReplacing(InspectReplacingPhraseContext ctx);

	List<For> getFors();

	List<Replacing> getReplacings();

}
