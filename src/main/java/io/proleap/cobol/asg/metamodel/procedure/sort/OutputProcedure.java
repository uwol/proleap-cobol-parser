/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import io.proleap.cobol.Cobol85Parser.SortOutputThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OutputProcedure extends CobolDivisionElement {

	OutputThrough addOutputThrough(SortOutputThroughContext ctx);

	OutputThrough getOutputThrough();

	Call getProcedureCall();

	void setProcedureCall(Call procedureCall);
}
