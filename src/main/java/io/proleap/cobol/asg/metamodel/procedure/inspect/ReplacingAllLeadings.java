/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ReplacingAllLeadings extends CobolDivisionElement {

	enum Type {
		ALL, FIRST, LEADING
	}

	ReplacingAllLeading addAllLeading(InspectReplacingAllLeadingContext ctx);

	List<ReplacingAllLeading> getAllLeadings();

	Type getType();

	void setType(Type type);

}
