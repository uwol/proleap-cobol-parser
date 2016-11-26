/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.returnstmt;

import io.proleap.cobol.Cobol85Parser.ReturnIntoContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.AtEnd;
import io.proleap.cobol.parser.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Obtains sorted or merged records from SORT or MERGE.
 */
public interface ReturnStatement extends Statement {

	void addFileCall(Call fileCall);

	Into addInto(ReturnIntoContext ctx);

	AtEnd getAtEnd();

	Call getFileCall();

	Into getInto();

	NotAtEnd getNotAtEnd();

	void setAtEnd(AtEnd atEnd);

	void setNotAtEnd(NotAtEnd notAtEnd);

}
