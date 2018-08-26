/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import io.proleap.cobol.CobolParser.InspectConvertingPhraseContext;
import io.proleap.cobol.CobolParser.InspectReplacingPhraseContext;
import io.proleap.cobol.CobolParser.InspectTallyingPhraseContext;
import io.proleap.cobol.CobolParser.InspectTallyingReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Tally, replace, or tally and replace occurrences of one or multiple
 * characters in a data item.
 */
public interface InspectStatement extends Statement {

	enum InspectType {
		CONVERTING, REPLACING, TALLYING, TALLYING_REPLACING
	}

	Converting addConverting(InspectConvertingPhraseContext ctx);

	Replacing addReplacing(InspectReplacingPhraseContext ctx);

	Tallying addTallying(InspectTallyingPhraseContext ctx);

	TallyingReplacing addTallyingReplacing(InspectTallyingReplacingPhraseContext ctx);

	Converting getConverting();

	/**
	 * Data item to be inspected.
	 */
	Call getDataItemCall();

	InspectType getInspectType();

	Replacing getReplacing();

	Tallying getTallying();

	TallyingReplacing getTallyingReplacing();

	void setDataItemCall(Call dataItemCall);

	void setInspectType(InspectType inspectType);
}
