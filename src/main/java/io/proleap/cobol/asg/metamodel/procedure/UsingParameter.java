/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import io.proleap.cobol.Cobol85Parser.ProcedureDivisionByReferencePhraseContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionByValuePhraseContext;
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
