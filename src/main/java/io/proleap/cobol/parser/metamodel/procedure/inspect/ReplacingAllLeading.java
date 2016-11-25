/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectByContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface ReplacingAllLeading extends CobolDivisionElement {

	BeforeAfter addBeforeAfter(InspectBeforeAfterContext ctx);

	By addBy(InspectByContext ctx);

	List<BeforeAfter> getBeforeAfters();

	By getBy();

	Call getPatternDataItemCall();

	void setPatternDataItemCall(Call patternDataItemCall);

}
