/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import io.proleap.cobol.CobolParser.ProcedureDivisionByReferencePhraseContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionByValuePhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsingParameter extends CobolDivisionElement {

	enum UsingParameterType {
		REFERENCE, VALUE
	}

	ByReferencePhrase addByReferencePhrase(ProcedureDivisionByReferencePhraseContext ctx);

	ByValuePhrase addByValuePhrase(ProcedureDivisionByValuePhraseContext ctx);

	ByReferencePhrase getByReferencePhrase();

	ByValuePhrase getByValuePhrase();

	UsingParameterType getUsingParameterType();

	void setUsingParameterType(UsingParameterType usingParameterType);
}
