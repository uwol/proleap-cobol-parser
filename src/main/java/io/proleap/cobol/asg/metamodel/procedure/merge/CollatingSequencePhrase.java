/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.MergeCollatingAlphanumericContext;
import io.proleap.cobol.Cobol85Parser.MergeCollatingNationalContext;
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
