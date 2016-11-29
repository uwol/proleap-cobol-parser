/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.returnstmt.impl;

import io.proleap.cobol.Cobol85Parser.ReturnIntoContext;
import io.proleap.cobol.Cobol85Parser.ReturnStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.AtEnd;
import io.proleap.cobol.parser.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.returnstmt.Into;
import io.proleap.cobol.parser.metamodel.procedure.returnstmt.ReturnStatement;

public class ReturnStatementImpl extends StatementImpl implements ReturnStatement {

	protected AtEnd atEnd;

	protected final ReturnStatementContext ctx;

	protected Call fileCall;

	protected Into into;

	protected NotAtEnd notAtEnd;

	public ReturnStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReturnStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public Into addInto(final ReturnIntoContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			final Call intoCall = createCall(ctx.qualifiedDataName());
			result.setIntoCall(intoCall);

			into = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEnd getAtEnd() {
		return atEnd;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public Into getInto() {
		return into;
	}

	@Override
	public NotAtEnd getNotAtEnd() {
		return notAtEnd;
	}

	@Override
	public void setAtEnd(final AtEnd atEnd) {
		this.atEnd = atEnd;
	}

	@Override
	public void setNotAtEnd(final NotAtEnd notAtEnd) {
		this.notAtEnd = notAtEnd;
	}

}
