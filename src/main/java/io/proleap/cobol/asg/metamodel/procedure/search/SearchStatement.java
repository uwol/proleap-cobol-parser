/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search;

import java.util.List;

import io.proleap.cobol.CobolParser.SearchVaryingContext;
import io.proleap.cobol.CobolParser.SearchWhenContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Searches a table for an element satisfying a condition. Adjusts index value
 * to point to the table element.
 */
public interface SearchStatement extends Statement {

	VaryingPhrase addVaryingPhrase(SearchVaryingContext ctx);

	WhenPhrase addWhenPhrase(SearchWhenContext ctx);

	AtEndPhrase getAtEndPhrase();

	Call getDataCall();

	VaryingPhrase getVaryingPhrase();

	List<WhenPhrase> getWhenPhrases();

	void setAtEndPhrase(AtEndPhrase atEndPhrase);

	void setDataCall(Call dataCall);
}
