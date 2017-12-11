/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.NamedElement;

/**
 * Specifies program name and assigns attributes to it.
 */
public interface ProgramIdParagraph extends CobolDivisionElement, NamedElement {

	enum Attribute {
		COMMON, DEFINITION, INITIAL, LIBRARY, RECURSIVE
	}

	Attribute getAttribute();

	void setAttribute(Attribute attribute);
}
