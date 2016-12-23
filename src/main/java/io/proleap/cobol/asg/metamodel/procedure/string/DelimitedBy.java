/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface DelimitedBy extends CobolDivisionElement {

	enum Type {
		CHARACTERS, SIZE
	}

	Call getCharactersCall();

	Type getType();

	void setCharactersCall(Call charactersCall);

	void setType(Type type);
}
