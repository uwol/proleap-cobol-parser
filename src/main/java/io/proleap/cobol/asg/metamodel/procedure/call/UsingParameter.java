/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.CobolParser.CallByContentPhraseContext;
import io.proleap.cobol.CobolParser.CallByReferencePhraseContext;
import io.proleap.cobol.CobolParser.CallByValuePhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsingParameter extends CobolDivisionElement {

	enum ParameterType {
		CONTENT, REFERENCE, VALUE
	}

	ByContentPhrase addByContentPhrase(CallByContentPhraseContext ctx);

	ByReferencePhrase addByReferencePhrase(CallByReferencePhraseContext ctx);

	ByValuePhrase addByValuePhrase(CallByValuePhraseContext ctx);

	ByContentPhrase getByContentPhrase();

	ByReferencePhrase getByReferencePhrase();

	ByValuePhrase getByValuePhrase();

	ParameterType getParameterType();

	void setParameterType(ParameterType parameterType);
}
