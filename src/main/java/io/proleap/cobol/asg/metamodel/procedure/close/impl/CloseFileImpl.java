/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.CobolParser.CloseFileContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOStatementContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingContext;
import io.proleap.cobol.CobolParser.CloseReelUnitStatementContext;
import io.proleap.cobol.CobolParser.CloseRelativeStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.asg.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseReelUnitStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseRelativeStatement;

public class CloseFileImpl extends CobolDivisionElementImpl implements CloseFile {

	protected CloseFileType closeFileType;

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
				withType = ClosePortFileIoStatement.WithType.NO_WAIT;
			} else if (ctx.WITH() != null && ctx.WAIT() != null) {
				withType = ClosePortFileIoStatement.WithType.WAIT;
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
			final CloseReelUnitStatement.CloseReelUnitType type;

			if (ctx.REEL() != null) {
				type = CloseReelUnitStatement.CloseReelUnitType.REEL;
			} else if (ctx.UNIT() != null) {
				type = CloseReelUnitStatement.CloseReelUnitType.UNIT;
			} else {
				type = null;
			}

			result.setCloseReelUnitType(type);

			// for removal
			if (ctx.REMOVAL() != null) {
				result.setForRemoval(true);
			}

			// with type
			final CloseReelUnitStatement.WithType withType;

			if (ctx.REWIND() != null) {
				withType = CloseReelUnitStatement.WithType.NO_REWIND;
			} else if (ctx.LOCK() != null) {
				withType = CloseReelUnitStatement.WithType.LOCK;
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
				withType = CloseRelativeStatement.WithType.NO_REWIND;
			} else if (ctx.LOCK() != null) {
				withType = CloseRelativeStatement.WithType.LOCK;
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
	public CloseFileType getCloseFileType() {
		return closeFileType;
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
	public void setCloseFileType(final CloseFileType closeFileType) {
		this.closeFileType = closeFileType;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
