/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByReferenceStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class CallStatementImpl extends StatementImpl implements CallStatement {

	protected List<CallByReferenceStatement> callByReferenceStatements = new ArrayList<CallByReferenceStatement>();

	protected final CallStatementContext ctx;

	public CallStatementImpl(final ProgramUnit programUnit, final CallStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CallByReferenceStatement addCallByReferenceStatement(final CallByReferenceStatementContext ctx) {
		CallByReferenceStatement result = (CallByReferenceStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallByReferenceStatementImpl(programUnit, ctx);

			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final ValueStmt referenceValueStmt = createCallValueStmt(identifierContext);
				result.addReferenceValueStmt(referenceValueStmt);
			}

			callByReferenceStatements.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<CallByReferenceStatement> getCallByReferenceStatements() {
		return callByReferenceStatements;
	}

}
