/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.ifstmt;

import io.proleap.cobol.parser.metamodel.StatementsContainer;

public interface Then extends StatementsContainer {

	boolean isNextSentence();

	void setNextSentence(boolean nextSentence);
}
