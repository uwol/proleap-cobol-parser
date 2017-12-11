/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.MnemonicName;

public interface ChannelClause extends CobolDivisionElement {

	IntegerLiteral getIntegerLiteral();

	MnemonicName getMnemonicName();

	void setIntegerLiteral(IntegerLiteral integerLiteral);

	void setMnemonicName(MnemonicName mnemonicName);

}
