/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import io.proleap.cobol.Cobol85Parser.UnstringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringTallyingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Separates data in a sending field and sends the data into multiple fields.
 */
public interface UnstringStatement extends Statement {

	Intos addIntos(UnstringIntoPhraseContext ctx);

	Sending addSending(UnstringSendingPhraseContext ctx);

	Tallying addTallying(UnstringTallyingPhraseContext ctx);

	WithPointer addWithPointer(UnstringWithPointerPhraseContext ctx);

	Intos getIntos();

	NotOnOverflow getNotOnOverflow();

	OnOverflow getOnOverflow();

	Sending getSending();

	Tallying getTallying();

	WithPointer getWithPointer();

	void setNotOnOverflow(NotOnOverflow notOnOverflow);

	void setOnOverflow(OnOverflow onOverflow);
}
