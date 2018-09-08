/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.CobolParser.InspectReplacingAllLeadingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ReplacingAllLeadings extends CobolDivisionElement {

	enum ReplacingAllLeadingsType {
		ALL, FIRST, LEADING
	}

	ReplacingAllLeading addAllLeading(InspectReplacingAllLeadingContext ctx);

	List<ReplacingAllLeading> getAllLeadings();

	ReplacingAllLeadingsType getReplacingAllLeadingsType();

	void setReplacingAllLeadingsType(ReplacingAllLeadingsType replacingAllLeadingsType);
}
