/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface DependingOn extends CobolDivisionElement {

	void addProcedureCall(Call procedureCall);

	Call getDependingOnCall();

	List<Call> getProcedureCalls();

	boolean isMoreLabels();

	void setDependingOnCall(Call dependingOnCall);

	void setMoreLabels(boolean moreLabels);
}
