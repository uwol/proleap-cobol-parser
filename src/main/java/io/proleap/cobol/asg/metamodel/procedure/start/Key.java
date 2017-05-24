/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface Key extends CobolDivisionElement {

	enum KeyType {
		EQUAL, GREATER, GREATER_OR_EQUAL
	}

	Call getComparisonCall();

	KeyType getKeyType();

	void setComparisonCall(Call comparisonCall);

	void setKeyType(KeyType keyType);
}
