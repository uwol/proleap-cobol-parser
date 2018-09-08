/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

/**
 * Adds elementary data items of the same name for two groups.
 */
public interface AddCorrespondingStatement extends CobolDivisionElement {

	Call getFromCall();

	To getTo();

	void setFromCall(Call fromCall);

	void setTo(To to);
}
