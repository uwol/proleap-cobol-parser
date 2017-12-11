/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Literal;

public interface ImportAttribute extends CobolDivisionElement {

	enum ImportAttributeType {
		BY_FUNCTION, BY_TITLE
	}

	Literal getFunctionLiteral();

	ImportAttributeType getImportAttributeType();

	Literal getParameterLiteral();

	Literal getTitleLiteral();

	void setFunctionLiteral(Literal functionLiteral);

	void setImportAttributeType(ImportAttributeType importAttributeType);

	void setParameterLiteral(Literal parameterLiteral);

	void setTitleLiteral(Literal titleLiteral);
}
