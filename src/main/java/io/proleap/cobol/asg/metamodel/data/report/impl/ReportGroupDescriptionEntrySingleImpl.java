/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntrySingle;

public class ReportGroupDescriptionEntrySingleImpl extends ReportGroupDescriptionEntryImpl
		implements ReportGroupDescriptionEntrySingle {

	protected final ReportGroupDescriptionEntryFormat2Context ctx;

	public ReportGroupDescriptionEntrySingleImpl(final String name, final ProgramUnit programUnit,
			final ReportGroupDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReportGroupDescriptionEntryType getReportGroupDescriptionEntryType() {
		return ReportGroupDescriptionEntryType.SINGLE;
	}

}
