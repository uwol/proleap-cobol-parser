/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SortCollatingAlphanumericContext;
import io.proleap.cobol.Cobol85Parser.SortCollatingNationalContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface CollatingSequence extends CobolDivisionElement {

	void addAlphabetCall(Call alphabetCall);

	Alphanumeric addAlphanumeric(SortCollatingAlphanumericContext ctx);

	National addNational(SortCollatingNationalContext ctx);

	List<Call> getAlphabetCalls();

	Alphanumeric getAlphaNumeric();

	National getNational();
}
