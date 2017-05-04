/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

public abstract class StatementImpl extends CobolDivisionElementImpl implements Statement {

	protected final Scope scope;

	public StatementImpl(final ProgramUnit programUnit, final Scope scope, final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.scope = scope;
	}

	@Override
	public Scope getScope() {
		return scope;
	}
}
