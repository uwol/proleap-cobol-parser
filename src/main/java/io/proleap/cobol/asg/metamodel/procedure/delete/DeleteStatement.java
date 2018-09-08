/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.delete;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

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
