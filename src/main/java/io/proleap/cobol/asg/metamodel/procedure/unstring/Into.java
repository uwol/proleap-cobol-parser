/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import io.proleap.cobol.Cobol85Parser.UnstringCountInContext;
import io.proleap.cobol.Cobol85Parser.UnstringDelimiterInContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface Into extends CobolDivisionElement {

	CountIn addCountIn(UnstringCountInContext ctx);

	DelimiterIn addDelimiterIn(UnstringDelimiterInContext ctx);

	CountIn getCountIn();

	DelimiterIn getDelimiterIn();

	Call getIntoCall();

	void setIntoCall(Call intoCall);
}
