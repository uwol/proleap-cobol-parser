/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start;

import io.proleap.cobol.Cobol85Parser.StartKeyContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Positions in relative or indexed files for sequential reading of records.
 */
public interface StartStatement extends Statement {

	Key addKey(StartKeyContext ctx);

	Call getFileCall();

	InvalidKey getInvalidKey();

	Key getKey();

	NotInvalidKey getNotInvalidKey();

	void setFileCall(Call fileCall);

	void setInvalidKey(InvalidKey invalidKey);

	void setNotInvalidKey(NotInvalidKey notInvalidKey);
}
