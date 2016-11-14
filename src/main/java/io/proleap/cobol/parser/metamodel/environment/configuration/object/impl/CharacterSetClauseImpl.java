/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.object.impl;

import io.proleap.cobol.Cobol85Parser.CharacterSetClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.CharacterSetClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class CharacterSetClauseImpl extends CobolDivisionElementImpl implements CharacterSetClause {

	protected final CharacterSetClauseContext ctx;

	public CharacterSetClauseImpl(final ProgramUnit programUnit, final CharacterSetClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
