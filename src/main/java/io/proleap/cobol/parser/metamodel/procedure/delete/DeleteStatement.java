/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.delete;

import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Removes a record from a mass storage file.
 */
public interface DeleteStatement extends Statement {

	Call getFileCall();

	InvalidKey getInvalidKey();

	NotInvalidKey getNotInvalidKey();

	boolean isRecord();

	void setFileCall(Call fileCall);

	void setInvalidKey(InvalidKey invalidKey);

	void setNotInvalidKey(NotInvalidKey notInvalidKey);

	void setRecord(boolean record);

}
