/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface OrganizationClause extends CobolDivisionElement {

	enum Mode {
		Indexed, Relative, Sequential
	}

	enum Type {
		Binary, Line, Record, RecordBinary
	}

	Mode getMode();

	Type getType();

	void setMode(Mode mode);

	void setType(Type type);
}
