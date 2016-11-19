/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import io.proleap.cobol.Cobol85Parser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryVertical;

public class ReportGroupDescriptionEntryVerticalImpl extends ReportGroupDescriptionEntryImpl
		implements ReportGroupDescriptionEntryVertical {

	protected final ReportGroupDescriptionEntryFormat1Context ctx;

	public ReportGroupDescriptionEntryVerticalImpl(final ProgramUnit programUnit,
			final ReportGroupDescriptionEntryFormat1Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return Type.Vertical;
	}

}
