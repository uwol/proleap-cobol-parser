/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.CobolParser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingCloseDispositionContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Using extends CobolDivisionElement {

	enum UsingType {
		ASSOCIATED_DATA, ASSOCIATED_DATA_LENGTH, CLOSE_DISPOSITION
	}

	AssociatedDataLengthPhrase addAssociatedDataLengthPhrase(ClosePortFileIOUsingAssociatedDataLengthContext ctx);

	AssociatedDataPhrase addAssociatedDataPhrase(ClosePortFileIOUsingAssociatedDataContext ctx);

	CloseDispositionPhrase addCloseDispositionPhrase(ClosePortFileIOUsingCloseDispositionContext ctx);

	AssociatedDataLengthPhrase getAssociatedDataLengthPhrase();

	AssociatedDataPhrase getAssociatedDataPhrase();

	CloseDispositionPhrase getCloseDispositionPhrase();

	UsingType getUsingType();

	void setUsingType(UsingType usingType);
}
