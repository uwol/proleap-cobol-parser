/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivisionBody;
import io.proleap.cobol.parser.metamodel.impl.CobolScopedElementImpl;

public class EnvironmentDivisionImpl extends CobolScopedElementImpl implements EnvironmentDivision {

	protected final EnvironmentDivisionContext ctx;

	protected List<EnvironmentDivisionBody> environmentDivisionBodies;

	public EnvironmentDivisionImpl(final CopyBook copyBook, final CobolScope superScope,
			final EnvironmentDivisionContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addEnvironmentDivisionBody(final EnvironmentDivisionBody environmentDivisionBody) {
		environmentDivisionBodies.add(environmentDivisionBody);
	}

	@Override
	public List<EnvironmentDivisionBody> getEnvironmentDivisionBodies() {
		return environmentDivisionBodies;
	}

}
