/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send;

import io.proleap.cobol.Cobol85Parser.SendAdvancingLinesContext;
import io.proleap.cobol.Cobol85Parser.SendAdvancingMnemonicContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Advancing extends CobolDivisionElement {

	enum PositionType {
		AFTER, BEFORE
	}

	enum Type {
		LINES, MNEMONIC, PAGE
	}

	AdvancingLines addAdvancingLines(SendAdvancingLinesContext ctx);

	AdvancingMnemonic addAdvancingMnemonic(SendAdvancingMnemonicContext ctx);

	AdvancingLines getAdvancingLines();

	AdvancingMnemonic getAdvancingMnemonic();

	PositionType getPositionType();

	Type getType();

	void setPositionType(PositionType positionType);

	void setType(Type type);
}
