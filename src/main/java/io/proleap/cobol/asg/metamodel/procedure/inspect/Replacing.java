/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingCharactersContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Replacing extends CobolDivisionElement {

	ReplacingAllLeadings addAllLeadings(InspectReplacingAllLeadingsContext ctx);

	ReplacingCharacters addCharacters(InspectReplacingCharactersContext ctx);

	List<ReplacingAllLeadings> getAllLeadings();

	List<ReplacingCharacters> getCharacters();
}
