/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ExportAttribute extends CobolDivisionElement {

	enum Sharing {
		DONT_CARE, PRIVATE, SHARED_BY_ALL, SHARED_BY_RUN_UNIT
	}

	Sharing getSharing();

	void setSharing(Sharing sharing);
}
