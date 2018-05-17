/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataTypeDefClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.TypeDefClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class TypeDefClauseImpl extends CobolDivisionElementImpl implements TypeDefClause {

	protected DataTypeDefClauseContext ctx;

	protected boolean typeDef;

	public TypeDefClauseImpl(final ProgramUnit programUnit, final DataTypeDefClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isTypeDef() {
		return typeDef;
	}

	@Override
	public void setTypeDef(final boolean typeDef) {
		this.typeDef = typeDef;
	}

}
