/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize;

import java.util.List;

import io.proleap.cobol.CobolParser.InitializeReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Initializes values of selected data fields to predetermined values.
 * Functionally equivalent to one or more MOVE statements.
 */
public interface InitializeStatement extends Statement {

	void addDataItemCall(Call dataItemCall);

	ReplacingPhrase addReplacingPhrase(InitializeReplacingPhraseContext ctx);

	List<Call> getDataItemCalls();

	ReplacingPhrase getReplacingPhrase();
}
