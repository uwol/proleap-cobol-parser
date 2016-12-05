/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface By extends CobolDivisionElement {

	enum Type {
		Alphabetic, Alphanumeric, AlphanumericEdited, Dbcs, Egcs, National, Numeric, NumericEdited
	}

	Type getType();

	Call getValueCall();

	void setType(Type type);

	void setValueCall(Call valueCall);
}
