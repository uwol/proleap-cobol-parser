/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOStatementContext;
import io.proleap.cobol.Cobol85Parser.CloseReelUnitStatementContext;
import io.proleap.cobol.Cobol85Parser.CloseRelativeStatementContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface CloseFile extends CobolDivisionElement {

	ClosePortFileIoStatement addClosePortFileIOStatement(ClosePortFileIOStatementContext ctx);

	CloseReelUnitStatement addCloseReelUnitStatement(CloseReelUnitStatementContext ctx);

	CloseRelativeStatement addCloseRelativeStatement(CloseRelativeStatementContext ctx);

	ClosePortFileIoStatement getClosePortFileIOStatement();

	CloseReelUnitStatement getCloseReelUnitStatement();

	CloseRelativeStatement getCloseRelativeStatement();

	Call getFileCall();

	void setFileCall(Call fileCall);

}
