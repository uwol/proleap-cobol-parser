/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InitializeReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Initializes values of selected data fields to predetermined values.
 * Functionally equivalent to one or more MOVE statements.
 */
public interface InitializeStatement extends Statement {

	void addDataItemCall(Call dataItemCall);

	Replacing addReplacing(InitializeReplacingPhraseContext ctx);

	List<Call> getDataItemCalls();

	Replacing getReplacing();

}
