/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface DebugOn extends CobolDivisionElement {

	enum Type {
		AllProcedures, AllReferences, File, Procedure
	}

	Call getOnCall();

	Type getType();

	void setOnCall(Call onCall);

	void setType(Type type);
}
