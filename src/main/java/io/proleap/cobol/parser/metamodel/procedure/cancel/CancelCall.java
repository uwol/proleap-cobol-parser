/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.cancel;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface CancelCall extends CobolDivisionElement {

	enum Type {
		ByFunction, ByTitle
	}

	Call getCall();

	Type getType();

	void setCall(Call call);

	void setType(Type type);
}
