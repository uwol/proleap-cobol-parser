/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send;

import io.proleap.cobol.CobolParser.SendAdvancingLinesContext;
import io.proleap.cobol.CobolParser.SendAdvancingMnemonicContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Advancing extends CobolDivisionElement {

	enum AdvancingType {
		LINES, MNEMONIC, PAGE
	}

	enum PositionType {
		AFTER, BEFORE
	}

	AdvancingLines addAdvancingLines(SendAdvancingLinesContext ctx);

	AdvancingMnemonic addAdvancingMnemonic(SendAdvancingMnemonicContext ctx);

	AdvancingLines getAdvancingLines();

	AdvancingMnemonic getAdvancingMnemonic();

	AdvancingType getAdvancingType();

	PositionType getPositionType();

	void setAdvancingType(AdvancingType advancingType);

	void setPositionType(PositionType positionType);
}
