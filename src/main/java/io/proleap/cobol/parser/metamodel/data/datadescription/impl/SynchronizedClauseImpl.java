/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataSynchronizedClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.SynchronizedClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
