/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface RerunEveryOf extends CobolDivisionElement {

	enum RerunEveryOfType {
		REEL, UNIT
	}

	Call getFileCall();

	RerunEveryOfType getRerunEveryOfType();

	void setFileCall(Call fileCall);

	void setRerunEveryOfType(RerunEveryOfType rerunEveryOfType);
}
