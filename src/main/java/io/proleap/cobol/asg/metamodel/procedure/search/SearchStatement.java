/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SearchVaryingContext;
import io.proleap.cobol.Cobol85Parser.SearchWhenContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Searches a table for an element satisfying a condition. Adjusts index value
 * to point to the table element.
 */
public interface SearchStatement extends Statement {

	Varying addVarying(SearchVaryingContext ctx);

	When addWhen(SearchWhenContext ctx);

	AtEndPhrase getAtEndPhrase();

	Call getDataCall();

	Varying getVarying();

	List<When> getWhens();

	void setAtEndPhrase(AtEndPhrase atEndPhrase);

	void setDataCall(Call dataCall);

}
