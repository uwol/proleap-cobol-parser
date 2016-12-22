/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation.impl;

import io.proleap.cobol.Cobol85Parser.AbbreviationRestContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.AbbreviationRest;

public class AbbreviationRestImpl extends ValueStmtImpl implements AbbreviationRest {

	protected AbbreviationRestContext ctx;

	public AbbreviationRestImpl(final ProgramUnit programUnit, final AbbreviationRestContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public String getValue() {
		return null;
	}

}
