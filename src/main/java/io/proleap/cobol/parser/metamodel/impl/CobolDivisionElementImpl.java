/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.ProgramUnit;

public abstract class CobolDivisionElementImpl extends ProgramUnitElementImpl implements CobolDivisionElement {

	protected final CobolDivision scope;

	public CobolDivisionElementImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final ParseTree ctx) {
		super(programUnit, ctx);

		this.scope = scope;
	}

	@Override
	public CobolDivision getScope() {
		return scope;
	}

}