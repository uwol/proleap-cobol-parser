/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open;

import java.util.List;

import io.proleap.cobol.CobolParser.OpenExtendStatementContext;
import io.proleap.cobol.CobolParser.OpenIOStatementContext;
import io.proleap.cobol.CobolParser.OpenInputStatementContext;
import io.proleap.cobol.CobolParser.OpenOutputStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Associates a logical file description with its physical representation. Makes
 * the file resource available for processing.
 */
public interface OpenStatement extends Statement {

	ExtendPhrase addExtendPhrase(OpenExtendStatementContext ctx);

	InputOutputPhrase addInputOutputPhrase(OpenIOStatementContext ctx);

	InputPhrase addInputPhrase(OpenInputStatementContext ctx);

	OutputPhrase addOutputPhrase(OpenOutputStatementContext ctx);

	List<ExtendPhrase> getExtendPhrases();

	List<InputOutputPhrase> getInputOutputPhrases();

	List<InputPhrase> getInputPhrases();

	List<OutputPhrase> getOutputPhrases();
}
