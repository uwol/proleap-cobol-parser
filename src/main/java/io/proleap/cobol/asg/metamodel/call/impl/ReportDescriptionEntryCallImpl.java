/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.asg.metamodel.type.Type;

public class ReportDescriptionEntryCallImpl extends CallImpl implements ReportDescriptionEntryCall {

	protected final CallType callType = CallType.REPORT_DESCRIPTION_ENTRY_CALL;

	protected ReportDescriptionEntry reportDescriptionEntry;

	public ReportDescriptionEntryCallImpl(final String name, final ReportDescriptionEntry reportDescriptionEntry,
			final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.reportDescriptionEntry = reportDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public ReportDescriptionEntry getReportDescriptionEntry() {
		return reportDescriptionEntry;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + ", reportDescriptionEntry=[" + reportDescriptionEntry + "]";
	}
}
