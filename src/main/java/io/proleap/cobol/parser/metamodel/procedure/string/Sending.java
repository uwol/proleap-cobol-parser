/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.string;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.StringDelimitedByPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringForPhraseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface Sending extends CobolDivisionElement {

	enum Type {
		DelimitedBy, For
	}

	DelimitedBy addDelimitedBy(StringDelimitedByPhraseContext ctx);

	For addFor(StringForPhraseContext ctx);

	void addSendingCall(Call sendingCall);

	DelimitedBy getDelimitedBy();

	For getFor();

	List<Call> getSendingCalls();

	Type getType();

	void setType(Type type);
}
