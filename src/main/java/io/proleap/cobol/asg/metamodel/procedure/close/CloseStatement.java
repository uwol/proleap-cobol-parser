/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Ends the processing of a file or a reel/unit of a file.
 */
public interface CloseStatement extends Statement {

	CloseFile addCloseFile(CloseFileContext ctx);

	List<CloseFile> getCloseFiles();
}
