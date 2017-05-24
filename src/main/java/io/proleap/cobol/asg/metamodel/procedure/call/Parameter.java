/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.Cobol85Parser.CallByContentPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallByReferencePhraseContext;
import io.proleap.cobol.Cobol85Parser.CallByValuePhraseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Parameter extends CobolDivisionElement {

	enum ParameterType {
		CONTENT, REFERENCE, VALUE
	}

	CallByContent addCallByContent(CallByContentPhraseContext ctx);

	CallByReference addCallByReference(CallByReferencePhraseContext ctx);

	CallByValue addCallByValue(CallByValuePhraseContext ctx);

	CallByContent getCallByContent();

	CallByReference getCallByReference();

	CallByValue getCallByValue();

	ParameterType getParameterType();

	void setParameterType(ParameterType parameterType);

}
