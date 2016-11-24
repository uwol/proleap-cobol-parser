/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.stop;

import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Halts execution of the program permanently or temporarily and optionally
 * displays text on operator display terminal (ODT).
 */
public interface StopStatement extends Statement {

	enum Type {
		StopRun, StopRunAndDisplay
	}

	Call getDisplayCall();

	Type getType();

	void setDisplayCall(Call displayCall);

	void setType(Type type);

}
