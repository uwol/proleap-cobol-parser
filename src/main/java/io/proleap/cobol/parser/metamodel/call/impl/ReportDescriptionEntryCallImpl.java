/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.data.report.ReportDescriptionEntry;

public class ReportDescriptionEntryCallImpl extends CallImpl implements ReportDescriptionEntryCall {

	protected ReportDescriptionEntry reportDescriptionEntry;

	public ReportDescriptionEntryCallImpl(final String name, final ReportDescriptionEntry reportDescriptionEntry,
			final ProgramUnit programUnit, final ParseTree ctx) {
		super(name, programUnit, ctx);

		this.reportDescriptionEntry = reportDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return CallType.ReportDescriptionEntryCall;
	}

	@Override
	public ReportDescriptionEntry getReportDescriptionEntry() {
		return reportDescriptionEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", reportDescriptionEntry=[" + reportDescriptionEntry + "]";
	}
}
