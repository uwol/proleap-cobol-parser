/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.write;

import io.proleap.cobol.Cobol85Parser.WriteAdvancingLinesContext;
import io.proleap.cobol.Cobol85Parser.WriteAdvancingMnemonicContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface Advancing extends CobolDivisionElement {

	enum PositionType {
		After, Before
	}

	enum Type {
		Lines, Mnemonic, Page
	}

	AdvancingLines addAdvancingLines(WriteAdvancingLinesContext ctx);

	AdvancingMnemonic addAdvancingMnemonic(WriteAdvancingMnemonicContext ctx);

	AdvancingLines getAdvancingLines();

	AdvancingMnemonic getAdvancingMnemonic();

	PositionType getPositionType();

	Type getType();

	void setPositionType(PositionType positionType);

	void setType(Type type);
}
