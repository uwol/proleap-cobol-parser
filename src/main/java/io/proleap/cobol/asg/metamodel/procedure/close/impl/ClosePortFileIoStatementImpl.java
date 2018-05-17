/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ClosePortFileIOStatementContext;
import io.proleap.cobol.CobolParser.ClosePortFileIOUsingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.Using;

public class ClosePortFileIoStatementImpl extends CobolDivisionElementImpl implements ClosePortFileIoStatement {

	protected ClosePortFileIOStatementContext ctx;

	protected List<Using> usings = new ArrayList<Using>();

	protected WithType withType;

	public ClosePortFileIoStatementImpl(final ProgramUnit programUnit, final ClosePortFileIOStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Using addUsing(final ClosePortFileIOUsingContext ctx) {
		Using result = (Using) getASGElement(ctx);

		if (result == null) {
			result = new UsingImpl(programUnit, ctx);

			// close disposition
			if (ctx.closePortFileIOUsingCloseDisposition() != null) {
				result.addCloseDispositionPhrase(ctx.closePortFileIOUsingCloseDisposition());
				result.setUsingType(Using.UsingType.CLOSE_DISPOSITION);
			}

			// associated data
			if (ctx.closePortFileIOUsingAssociatedData() != null) {
				result.addAssociatedDataPhrase(ctx.closePortFileIOUsingAssociatedData());
				result.setUsingType(Using.UsingType.ASSOCIATED_DATA);
			}

			// associated data length
			if (ctx.closePortFileIOUsingAssociatedDataLength() != null) {
				result.addAssociatedDataLengthPhrase(ctx.closePortFileIOUsingAssociatedDataLength());
				result.setUsingType(Using.UsingType.ASSOCIATED_DATA_LENGTH);
			}

			usings.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Using> getUsings() {
		return usings;
	}

	@Override
	public WithType getWithType() {
		return withType;
	}

	@Override
	public void setWithType(final WithType withType) {
		this.withType = withType;
	}

}
