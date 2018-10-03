/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.CobolParser.ClosePortFileIOStatementContext;
import io.proleap.cobol.CobolParser.CloseReelUnitStatementContext;
import io.proleap.cobol.CobolParser.CloseRelativeStatementContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface CloseFile extends CobolDivisionElement {

	enum CloseFileType {
		PORT_FILE_IO, REEL_UNIT, RELATIVE
	}

	ClosePortFileIoStatement addClosePortFileIOStatement(ClosePortFileIOStatementContext ctx);

	CloseReelUnitStatement addCloseReelUnitStatement(CloseReelUnitStatementContext ctx);

	CloseRelativeStatement addCloseRelativeStatement(CloseRelativeStatementContext ctx);

	CloseFileType getCloseFileType();

	ClosePortFileIoStatement getClosePortFileIOStatement();

	CloseReelUnitStatement getCloseReelUnitStatement();

	CloseRelativeStatement getCloseRelativeStatement();

	Call getFileCall();

	void setCloseFileType(CloseFileType closeFileType);

	void setFileCall(Call fileCall);
}
