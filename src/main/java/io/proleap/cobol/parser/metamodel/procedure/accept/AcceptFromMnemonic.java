/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

/**
 * Transfers data from hardware device.
 */
public interface AcceptFromMnemonic extends CobolDivisionElement {

	Call getMnemonicCall();

	void setMnemonicCall(Call mnemonicCall);
}
