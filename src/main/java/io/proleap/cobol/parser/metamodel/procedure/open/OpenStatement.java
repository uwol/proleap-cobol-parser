/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.open;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.OpenExtendStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenIOStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenInputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputStatementContext;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Associates a file description with its physical representation.
 */
public interface OpenStatement extends Statement {

	OpenExtend addOpenExtend(OpenExtendStatementContext ctx);

	OpenInput addOpenInput(OpenInputStatementContext ctx);

	OpenInputOutput addOpenInputOutput(OpenIOStatementContext ctx);

	OpenOutput addOpenOutput(OpenOutputStatementContext ctx);

	List<OpenExtend> getOpenExtends();

	List<OpenInputOutput> getOpenInputOutputs();

	List<OpenInput> getOpenInputs();

	List<OpenOutput> getOpenOutputs();

}
