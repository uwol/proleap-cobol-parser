/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingCloseDispositionContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.Using;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedData;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedDataLength;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingCloseDisposition;

public class UsingImpl extends CobolDivisionElementImpl implements Using {

	protected ClosePortFileIOUsingContext ctx;

	protected Type type;

	protected UsingAssociatedData usingAssociatedData;

	protected UsingAssociatedDataLength usingAssociatedDataLength;

	protected UsingCloseDisposition usingCloseDisposition;

	public UsingImpl(final ProgramUnit programUnit, final ClosePortFileIOUsingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public UsingAssociatedData addUsingAssociatedData(final ClosePortFileIOUsingAssociatedDataContext ctx) {
		UsingAssociatedData result = (UsingAssociatedData) getASGElement(ctx);

		if (result == null) {
			result = new UsingAssociatedDataImpl(programUnit, ctx);

			// data call
			final Call dataCall = createCall(ctx.identifier(), ctx.integerLiteral());
			result.setDataCall(dataCall);

			usingAssociatedData = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingAssociatedDataLength addUsingAssociatedDataLength(
			final ClosePortFileIOUsingAssociatedDataLengthContext ctx) {
		UsingAssociatedDataLength result = (UsingAssociatedDataLength) getASGElement(ctx);

		if (result == null) {
			result = new UsingAssociatedDataLengthImpl(programUnit, ctx);

			// data call
			final Call dataLengthCall = createCall(ctx.identifier(), ctx.integerLiteral());
			result.setDataLengthCall(dataLengthCall);

			usingAssociatedDataLength = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingCloseDisposition addUsingCloseDisposition(final ClosePortFileIOUsingCloseDispositionContext ctx) {
		UsingCloseDisposition result = (UsingCloseDisposition) getASGElement(ctx);

		if (result == null) {
			result = new UsingCloseDispositionImpl(programUnit, ctx);

			// type
			final UsingCloseDisposition.Type type;

			if (ctx.ABORT() != null) {
				type = UsingCloseDisposition.Type.ABORT;
			} else if (ctx.ORDERLY() != null) {
				type = UsingCloseDisposition.Type.ORDERLY;
			} else {
				type = null;
			}

			result.setType(type);

			usingCloseDisposition = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public UsingAssociatedData getUsingAssociatedData() {
		return usingAssociatedData;
	}

	@Override
	public UsingAssociatedDataLength getUsingAssociatedDataLength() {
		return usingAssociatedDataLength;
	}

	@Override
	public UsingCloseDisposition getUsingCloseDisposition() {
		return usingCloseDisposition;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
