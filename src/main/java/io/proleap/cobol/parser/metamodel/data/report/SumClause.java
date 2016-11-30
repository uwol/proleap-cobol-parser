/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report;

import java.util.List;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface SumClause extends CobolDivisionElement {

	void addSumCall(Call sumCall);

	void addUponCall(Call uponCall);

	List<Call> getSumCalls();

	List<Call> getUponCalls();
}
