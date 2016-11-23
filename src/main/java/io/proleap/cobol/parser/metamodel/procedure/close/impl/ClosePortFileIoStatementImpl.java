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

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOStatementContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.Using;

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
				result.addUsingCloseDisposition(ctx.closePortFileIOUsingCloseDisposition());
				result.setType(Using.Type.CloseDisposition);
			}

			// associated data
			if (ctx.closePortFileIOUsingAssociatedData() != null) {
				result.addUsingAssociatedData(ctx.closePortFileIOUsingAssociatedData());
				result.setType(Using.Type.AssociatedData);
			}

			// associated data length
			if (ctx.closePortFileIOUsingAssociatedDataLength() != null) {
				result.addUsingAssociatedDataLength(ctx.closePortFileIOUsingAssociatedDataLength());
				result.setType(Using.Type.AssociatedDataLength);
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
