/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataSynchronizedClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.SynchronizedClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SynchronizedClauseImpl extends CobolDivisionElementImpl implements SynchronizedClause {

	protected DataSynchronizedClauseContext ctx;

	protected Synchronized sync;

	public SynchronizedClauseImpl(final ProgramUnit programUnit, final DataSynchronizedClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Synchronized getSynchronized() {
		return sync;
	}

	@Override
	public void setSynchronized(final Synchronized sync) {
		this.sync = sync;
	}

}
