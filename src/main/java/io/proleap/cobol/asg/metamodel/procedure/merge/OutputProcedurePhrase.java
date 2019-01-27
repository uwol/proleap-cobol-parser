/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge;

import java.util.List;

import io.proleap.cobol.CobolParser.MergeOutputThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OutputProcedurePhrase extends CobolDivisionElement {

	void addCall(Call call);

	void addCalls(List<Call> calls);

	OutputThrough addOutputThrough(MergeOutputThroughContext ctx);

	List<Call> getCalls();

	OutputThrough getOutputThrough();

	Call getProcedureCall();

	void setProcedureCall(Call procedureCall);
}
