/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.CobolParser.InspectToContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface Converting extends CobolDivisionElement {

	BeforeAfterPhrase addBeforeAfterPhrase(InspectBeforeAfterContext ctx);

	To addTo(InspectToContext ctx);

	List<BeforeAfterPhrase> getBeforeAfterPhrases();

	ValueStmt getFromValueStmt();

	To getTo();

	void setFromValueStmt(ValueStmt fromValueStmt);
}
