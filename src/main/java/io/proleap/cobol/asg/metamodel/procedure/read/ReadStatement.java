/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read;

import io.proleap.cobol.Cobol85Parser.ReadIntoContext;
import io.proleap.cobol.Cobol85Parser.ReadKeyContext;
import io.proleap.cobol.Cobol85Parser.ReadWithContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
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

	AtEnd getAtEnd();

	Call getFileCall();

	Into getInto();

	InvalidKey getInvalidKey();

	Key getKey();

	NotAtEnd getNotAtEnd();

	NotInvalidKey getNotInvalidKey();

	With getWith();

	boolean isNextRecord();

	void setAtEnd(AtEnd atEnd);

	void setFileCall(Call fileCall);

	void setInvalidKey(InvalidKey invalidKey);

	void setNextRecord(boolean nextRecord);

	void setNotAtEnd(NotAtEnd notAtEnd);

	void setNotInvalidKey(NotInvalidKey notInvalidKey);
}
