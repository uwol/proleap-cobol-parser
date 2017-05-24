/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.stop;

import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Halts execution of the program permanently or temporarily and optionally
 * displays text on operator display terminal (ODT).
 */
public interface StopStatement extends Statement {

	enum StopType {
		STOP_RUN, STOP_RUN_AND_DISPLAY
	}

	ValueStmt getDisplayValueStmt();

	StopType getStopType();

	void setDisplayValueStmt(ValueStmt displayValueStmt);

	void setStopType(StopType stopType);

}
