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
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface AllLeading extends CobolDivisionElement {

	BeforeAfterPhrase addBeforeAfterPhrase(InspectBeforeAfterContext ctx);

	List<BeforeAfterPhrase> getBeforeAfterPhrases();

	ValueStmt getPatternDataItemValueStmt();

	void setPatternDataItemValueStmt(ValueStmt patternDataItemValueStmt);
}
