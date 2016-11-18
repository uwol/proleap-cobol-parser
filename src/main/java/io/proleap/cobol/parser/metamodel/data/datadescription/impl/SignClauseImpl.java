/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataSignClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.SignClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class SignClauseImpl extends CobolDivisionElementImpl implements SignClause {

	protected DataSignClauseContext ctx;

	protected Boolean separate;

	protected Type type;

	public SignClauseImpl(final ProgramUnit programUnit, final DataSignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Boolean isSeparate() {
		return separate;
	}

	@Override
	public void setSeparate(final Boolean separate) {
		this.separate = separate;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
