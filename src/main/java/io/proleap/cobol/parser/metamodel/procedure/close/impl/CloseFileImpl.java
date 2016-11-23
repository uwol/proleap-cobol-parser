/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOStatementContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingContext;
import io.proleap.cobol.Cobol85Parser.CloseReelUnitStatementContext;
import io.proleap.cobol.Cobol85Parser.CloseRelativeStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.parser.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseReelUnitStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseRelativeStatement;

public class CloseFileImpl extends CobolDivisionElementImpl implements CloseFile {

	protected ClosePortFileIoStatement closePortFileIoStatement;

	protected CloseReelUnitStatement closeReelUnitStatement;

	protected CloseRelativeStatement closeRelativeStatement;

	protected CloseFileContext ctx;

	protected Call fileCall;

	public CloseFileImpl(final ProgramUnit programUnit, final CloseFileContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ClosePortFileIoStatement addClosePortFileIOStatement(final ClosePortFileIOStatementContext ctx) {
		ClosePortFileIoStatement result = (ClosePortFileIoStatement) getASGElement(ctx);

		if (result == null) {
			result = new ClosePortFileIoStatementImpl(programUnit, ctx);

			// with type
			final ClosePortFileIoStatement.WithType withType;

			if (ctx.NO() != null && ctx.WAIT() != null) {
				withType = ClosePortFileIoStatement.WithType.NoWait;
			} else if (ctx.WITH() != null && ctx.WAIT() != null) {
				withType = ClosePortFileIoStatement.WithType.Wait;
			} else {
				withType = null;
			}

			result.setWithType(withType);

			for (final ClosePortFileIOUsingContext closePortFileIOUsingContext : ctx.closePortFileIOUsing()) {
				result.addUsing(closePortFileIOUsingContext);
			}

			closePortFileIoStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CloseReelUnitStatement addCloseReelUnitStatement(final CloseReelUnitStatementContext ctx) {
		CloseReelUnitStatement result = (CloseReelUnitStatement) getASGElement(ctx);

		if (result == null) {
			result = new CloseReelUnitStatementImpl(programUnit, ctx);

			// type
			final CloseReelUnitStatement.Type type;

			if (ctx.REEL() != null) {
				type = CloseReelUnitStatement.Type.Reel;
			} else if (ctx.UNIT() != null) {
				type = CloseReelUnitStatement.Type.Unit;
			} else {
				type = null;
			}

			result.setType(type);

			// for removal
			if (ctx.REMOVAL() != null) {
				result.setForRemoval(true);
			}

			// with type
			final CloseReelUnitStatement.WithType withType;

			if (ctx.REWIND() != null) {
				withType = CloseReelUnitStatement.WithType.NoRewind;
			} else if (ctx.LOCK() != null) {
				withType = CloseReelUnitStatement.WithType.Lock;
			} else {
				withType = null;
			}

			result.setWithType(withType);

			closeReelUnitStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CloseRelativeStatement addCloseRelativeStatement(final CloseRelativeStatementContext ctx) {
		CloseRelativeStatement result = (CloseRelativeStatement) getASGElement(ctx);

		if (result == null) {
			result = new CloseRelativeStatementImpl(programUnit, ctx);

			// with type
			final CloseRelativeStatement.WithType withType;

			if (ctx.REWIND() != null) {
				withType = CloseRelativeStatement.WithType.NoRewind;
			} else if (ctx.LOCK() != null) {
				withType = CloseRelativeStatement.WithType.Lock;
			} else {
				withType = null;
			}

			result.setWithType(withType);

			closeRelativeStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ClosePortFileIoStatement getClosePortFileIOStatement() {
		return closePortFileIoStatement;
	}

	@Override
	public CloseReelUnitStatement getCloseReelUnitStatement() {
		return closeReelUnitStatement;
	}

	@Override
	public CloseRelativeStatement getCloseRelativeStatement() {
		return closeRelativeStatement;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
