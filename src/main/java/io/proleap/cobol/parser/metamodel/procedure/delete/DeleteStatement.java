/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.delete;

import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Removes a record from a mass storage file.
 */
public interface DeleteStatement extends Statement {

	Call getFileCall();

	InvalidKeyPhrase getInvalidKeyPhrase();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	boolean isRecord();

	void setFileCall(Call fileCall);

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKeyPhrase);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKeyPhrase);

	void setRecord(boolean record);

}
