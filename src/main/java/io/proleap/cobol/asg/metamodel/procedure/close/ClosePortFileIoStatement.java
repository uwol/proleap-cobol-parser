/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import java.util.List;

import io.proleap.cobol.CobolParser.ClosePortFileIOUsingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ClosePortFileIoStatement extends CobolDivisionElement {

	enum WithType {
		NO_WAIT, WAIT
	}

	Using addUsing(ClosePortFileIOUsingContext ctx);

	List<Using> getUsings();

	WithType getWithType();

	void setWithType(WithType withType);
}
