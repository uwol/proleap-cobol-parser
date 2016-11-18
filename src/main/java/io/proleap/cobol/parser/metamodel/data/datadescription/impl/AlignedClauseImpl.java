/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataAlignedClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.AlignedClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class AlignedClauseImpl extends CobolDivisionElementImpl implements AlignedClause {

	protected Boolean aligned;

	protected DataAlignedClauseContext ctx;

	public AlignedClauseImpl(final ProgramUnit programUnit, final DataAlignedClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Boolean isAligned() {
		return aligned;
	}

	@Override
	public void setAligned(final Boolean aligned) {
		this.aligned = aligned;
	}

}
