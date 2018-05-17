/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge;

import java.util.List;

import io.proleap.cobol.CobolParser.MergeCollatingAlphanumericContext;
import io.proleap.cobol.CobolParser.MergeCollatingNationalContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface CollatingSequencePhrase extends CobolDivisionElement {

	void addAlphabetCall(Call alphabetCall);

	Alphanumeric addAlphanumeric(MergeCollatingAlphanumericContext ctx);

	National addNational(MergeCollatingNationalContext ctx);

	List<Call> getAlphabetCalls();

	Alphanumeric getAlphaNumeric();

	National getNational();
}
