/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.generate;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Controls report production.
 */
public interface GenerateStatement extends Statement {

	Call getReportDescriptionCall();

	void setReportDescriptionCall(Call reportDescriptionCall);
}
