/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.CobolParser.CharacterSetClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.CharacterSetClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class CharacterSetClauseImpl extends CobolDivisionElementImpl implements CharacterSetClause {

	protected final CharacterSetClauseContext ctx;

	public CharacterSetClauseImpl(final ProgramUnit programUnit, final CharacterSetClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
