/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.CobolParser.InspectAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectCharactersContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface For extends CobolDivisionElement {

	AllLeadingPhrase addAllLeadingPhrase(InspectAllLeadingsContext ctx);

	Characters addCharacters(InspectCharactersContext ctx);

	List<AllLeadingPhrase> getAllLeadingPhrase();

	List<Characters> getCharacters();

	/**
	 * Data item the tally count is accumulated in.
	 */
	Call getTallyCountDataItemCall();

	void setTallyCountDataItemCall(Call tallyCountDataItemCall);
}
