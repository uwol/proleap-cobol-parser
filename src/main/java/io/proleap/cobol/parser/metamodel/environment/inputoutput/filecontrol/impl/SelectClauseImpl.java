/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.SelectClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.SelectClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SelectClauseImpl extends CobolDivisionElementImpl implements SelectClause {

	protected final SelectClauseContext ctx;

	protected final String name;

	public SelectClauseImpl(final String name, final ProgramUnit programUnit, final SelectClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
