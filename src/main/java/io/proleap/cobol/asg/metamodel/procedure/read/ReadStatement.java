/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read;

import io.proleap.cobol.CobolParser.ReadIntoContext;
import io.proleap.cobol.CobolParser.ReadKeyContext;
import io.proleap.cobol.CobolParser.ReadWithContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * For sequential file access makes the next logical record from a file
 * available. For random access makes available a specified record from a
 * direct-access file.
 */
public interface ReadStatement extends Statement {

	Into addInto(ReadIntoContext ctx);

	Key addKey(ReadKeyContext ctx);

	With addWith(ReadWithContext ctx);

	AtEndPhrase getAtEnd();

	Call getFileCall();

	Into getInto();

	InvalidKeyPhrase getInvalidKeyPhrase();

	Key getKey();

	NotAtEndPhrase getNotAtEndPhrase();

	NotInvalidKeyPhrase getNotInvalidKeyPhrase();

	With getWith();

	boolean isNextRecord();

	void setAtEnd(AtEndPhrase atEnd);

	void setFileCall(Call fileCall);

	void setInvalidKeyPhrase(InvalidKeyPhrase invalidKey);

	void setNextRecord(boolean nextRecord);

	void setNotAtEndPhrase(NotAtEndPhrase notAtEnd);

	void setNotInvalidKeyPhrase(NotInvalidKeyPhrase notInvalidKey);
}
