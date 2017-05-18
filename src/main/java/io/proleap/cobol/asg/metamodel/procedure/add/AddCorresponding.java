/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

/**
 * Adds elementary data items of the same name for two groups.
 */
public interface AddCorresponding extends CobolDivisionElement {

	Call getFrom();

	To getTo();

	void setFrom(Call from);

	void setTo(To to);

}
