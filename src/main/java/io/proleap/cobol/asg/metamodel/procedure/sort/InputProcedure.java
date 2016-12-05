/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import io.proleap.cobol.Cobol85Parser.SortInputThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface InputProcedure extends CobolDivisionElement {

	InputThrough addInputThrough(SortInputThroughContext ctx);

	InputThrough getInputThrough();

	Call getProcedureCall();

	void setProcedureCall(Call procedureCall);
}
