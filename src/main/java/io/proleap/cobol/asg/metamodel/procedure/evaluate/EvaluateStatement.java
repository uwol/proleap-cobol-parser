/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate;

import java.util.List;

import io.proleap.cobol.CobolParser.EvaluateAlsoSelectContext;
import io.proleap.cobol.CobolParser.EvaluateSelectContext;
import io.proleap.cobol.CobolParser.EvaluateWhenOtherContext;
import io.proleap.cobol.CobolParser.EvaluateWhenPhraseContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Shorthand notation for a series of nested if statements.
 */
public interface EvaluateStatement extends Statement {

	AlsoSelect addAlsoSelect(EvaluateAlsoSelectContext ctx);

	Select addSelect(EvaluateSelectContext ctx);

	WhenOther addWhenOther(EvaluateWhenOtherContext ctx);

	WhenPhrase addWhenPhrase(EvaluateWhenPhraseContext ctx);

	List<AlsoSelect> getAlsoSelects();

	Select getSelect();

	WhenOther getWhenOther();

	List<WhenPhrase> getWhenPhrases();
}