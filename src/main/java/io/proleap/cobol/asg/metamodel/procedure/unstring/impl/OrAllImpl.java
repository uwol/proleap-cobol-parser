/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring.impl;

import io.proleap.cobol.CobolParser.UnstringOrAllPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.OrAll;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class OrAllImpl extends CobolDivisionElementImpl implements OrAll {

	protected final UnstringOrAllPhraseContext ctx;

	protected ValueStmt orAllValueStmt;

	public OrAllImpl(final ProgramUnit programUnit, final UnstringOrAllPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getOrAllValueStmt() {
		return orAllValueStmt;
	}

	@Override
	public void setOrAllValueStmt(final ValueStmt orAllValueStmt) {
		this.orAllValueStmt = orAllValueStmt;
	}

}
