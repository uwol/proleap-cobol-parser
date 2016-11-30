/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface SymbolicSubQueueClause extends CobolDivisionElement {

	enum Type {
		SubQueue1, SubQueue2, SubQueue3
	}

	Call getDataDescCall();

	Type getType();

	void setDataDescCall(Call dataDescCall);

	void setType(Type type);

}
