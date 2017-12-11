/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

/**
 * Causes transfer of control depend on the call returning 1, 2 etc. If the
 * value is anything other no transfer occurs and control passes to the next
 * statement.
 */
public interface DependingOnPhrase extends CobolDivisionElement {

	void addProcedureCall(Call procedureCall);

	Call getDependingOnCall();

	List<Call> getProcedureCalls();

	boolean isMoreLabels();

	void setDependingOnCall(Call dependingOnCall);

	void setMoreLabels(boolean moreLabels);
}
