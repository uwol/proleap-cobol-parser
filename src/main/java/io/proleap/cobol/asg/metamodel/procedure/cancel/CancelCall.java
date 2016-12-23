/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.cancel;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface CancelCall extends CobolDivisionElement {

	enum Type {
		BY_FUNCTION, BY_TITLE
	}

	Call getCall();

	Type getType();

	void setCall(Call call);

	void setType(Type type);
}
