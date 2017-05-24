/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ByReference extends CobolDivisionElement {

	enum ByReferenceType {
		ADDRESS_OF, INTEGER, STRING
	}

	ByReferenceType getByReferenceType();

	Call getCall();

	void setByReferenceType(ByReferenceType byReferenceType);

	void setCall(Call call);
}
