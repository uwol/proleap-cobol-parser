/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface Giving extends CobolDivisionElement {

	enum CloseProcedure {
		CRUNCH, LOCK, NO_REWIND, RELEASE, SAVE, WITH_REMOVE_CRUNCH
	}

	CloseProcedure getCloseProcedure();

	Call getFileCall();

	void setCloseProcedure(CloseProcedure closeProcedure);

	void setFileCall(Call fileCall);
}
