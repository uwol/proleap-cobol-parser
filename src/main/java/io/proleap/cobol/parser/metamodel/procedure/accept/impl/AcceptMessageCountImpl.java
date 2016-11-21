/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptMessageCountContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptMessageCount;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class AcceptMessageCountImpl extends StatementImpl implements AcceptMessageCount {

	protected final AcceptMessageCountContext ctx;

	public AcceptMessageCountImpl(final ProgramUnit programUnit, final AcceptMessageCountContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
