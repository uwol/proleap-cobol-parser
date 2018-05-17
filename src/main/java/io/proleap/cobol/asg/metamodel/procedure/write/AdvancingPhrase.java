/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.write;

import io.proleap.cobol.CobolParser.WriteAdvancingLinesContext;
import io.proleap.cobol.CobolParser.WriteAdvancingMnemonicContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface AdvancingPhrase extends CobolDivisionElement {

	enum AdvancingType {
		LINES, MNEMONIC, PAGE
	}

	enum PositionType {
		AFTER, BEFORE
	}

	AdvancingLines addAdvancingLines(WriteAdvancingLinesContext ctx);

	AdvancingMnemonic addAdvancingMnemonic(WriteAdvancingMnemonicContext ctx);

	AdvancingLines getAdvancingLines();

	AdvancingMnemonic getAdvancingMnemonic();

	AdvancingType getAdvancingType();

	PositionType getPositionType();

	void setAdvancingType(AdvancingType advancingType);

	void setPositionType(PositionType positionType);
}
