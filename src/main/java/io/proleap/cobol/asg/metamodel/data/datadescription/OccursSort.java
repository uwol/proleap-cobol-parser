/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface OccursSort extends CobolDivisionElement {

	enum Order {
		ASCENDING, DESCENDING
	}

	void addKeyCall(Call keyCall);

	List<Call> getKeyCalls();

	Order getOrder();

	void setOrder(Order order);
}
