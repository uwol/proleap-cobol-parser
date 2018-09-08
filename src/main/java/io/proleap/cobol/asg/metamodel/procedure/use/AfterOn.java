/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface AfterOn extends CobolDivisionElement {

	enum AfterOnType {
		EXTEND, FILE, INPUT, INPUT_OUTPUT, OUTPUT
	}

	void addFileCall(Call fileCall);

	AfterOnType getAfterOnType();

	List<Call> getFileCalls();

	void setType(AfterOnType afterOnType);
}
