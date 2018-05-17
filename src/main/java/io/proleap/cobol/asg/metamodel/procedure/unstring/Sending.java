/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import java.util.List;

import io.proleap.cobol.CobolParser.UnstringDelimitedByPhraseContext;
import io.proleap.cobol.CobolParser.UnstringOrAllPhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface Sending extends CobolDivisionElement {

	DelimitedByPhrase addDelimitedByPhrase(UnstringDelimitedByPhraseContext ctx);

	OrAll addOrAll(UnstringOrAllPhraseContext ctx);

	DelimitedByPhrase getDelimitedByPhrase();

	List<OrAll> getOrAlls();

	Call getSendingCall();

	void setSendingCall(Call sendingCall);
}
