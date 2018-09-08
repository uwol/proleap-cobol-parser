/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.CobolParser.InspectAllLeadingContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface AllLeadingPhrase extends CobolDivisionElement {

	enum AllLeadingsType {
		ALL, LEADING
	}

	AllLeading addAllLeading(InspectAllLeadingContext ctx);

	List<AllLeading> getAllLeadings();

	AllLeadingsType getAllLeadingsType();

	void setAllLeadingsType(AllLeadingsType allLeadingsType);
}
