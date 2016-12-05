/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.ReserveNetworkClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ReserveNetworkClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ReserveNetworkClauseImpl extends CobolDivisionElementImpl implements ReserveNetworkClause {

	protected final ReserveNetworkClauseContext ctx;

	public ReserveNetworkClauseImpl(final ProgramUnit programUnit, final ReserveNetworkClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
