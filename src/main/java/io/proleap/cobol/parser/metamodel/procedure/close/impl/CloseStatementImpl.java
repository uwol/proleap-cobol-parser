/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.close.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.Cobol85Parser.CloseStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class CloseStatementImpl extends StatementImpl implements CloseStatement {

	private final static Logger LOG = LogManager.getLogger(CloseStatementImpl.class);

	protected List<CloseFile> closeFiles = new ArrayList<CloseFile>();

	protected final CloseStatementContext ctx;

	public CloseStatementImpl(final ProgramUnit programUnit, final CloseStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CloseFile addCloseFile(final CloseFileContext ctx) {
		CloseFile result = (CloseFile) getASGElement(ctx);

		if (result == null) {
			result = new CloseFileImpl(programUnit, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// close reel unit
			if (ctx.closeReelUnitStatement() != null) {
				result.addCloseReelUnitStatement(ctx.closeReelUnitStatement());
			}

			// close relative
			if (ctx.closeRelativeStatement() != null) {
				result.addCloseRelativeStatement(ctx.closeRelativeStatement());
			}

			// close port io
			if (ctx.closePortFileIOStatement() != null) {
				result.addClosePortFileIOStatement(ctx.closePortFileIOStatement());
			}

			closeFiles.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<CloseFile> getCloseFiles() {
		return closeFiles;
	}

}
