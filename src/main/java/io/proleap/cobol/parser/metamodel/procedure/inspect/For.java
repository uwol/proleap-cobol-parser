/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectCharactersContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface For extends CobolDivisionElement {

	AllLeadings addAllLeadings(InspectAllLeadingsContext ctx);

	Characters addCharacters(InspectCharactersContext ctx);

	List<AllLeadings> getAllLeadings();

	List<Characters> getCharacters();

	/**
	 * Data item the tally count is accumulated in.
	 */
	Call getTallyCountDataItemCall();

	void setTallyCountDataItemCall(Call tallyCountDataItemCall);
}
