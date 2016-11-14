/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.OrganizationClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.OrganizationClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class OrganizationClauseImpl extends CobolDivisionElementImpl implements OrganizationClause {

	protected final OrganizationClauseContext ctx;

	protected Mode mode;

	protected Type type;

	public OrganizationClauseImpl(final ProgramUnit programUnit, final OrganizationClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Mode getMode() {
		return mode;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setMode(final Mode mode) {
		this.mode = mode;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
