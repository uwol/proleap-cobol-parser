/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OnKey extends CobolDivisionElement {

	enum OnKeyType {
		ASCENDING, DESCENDING
	}

	void addKeyCall(Call keyCall);

	List<Call> getKeyCalls();

	OnKeyType getOnKeyType();

	void setOnKeyType(OnKeyType onKeyType);
}
