/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.UnstringDelimitedByPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringOrAllPhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface Sending extends CobolDivisionElement {

	DelimitedBy addDelimitedBy(UnstringDelimitedByPhraseContext ctx);

	OrAll addOrAll(UnstringOrAllPhraseContext ctx);

	DelimitedBy getDelimitedBy();

	List<OrAll> getOrAlls();

	Call getSendingCall();

	void setSendingCall(Call sendingCall);
}
