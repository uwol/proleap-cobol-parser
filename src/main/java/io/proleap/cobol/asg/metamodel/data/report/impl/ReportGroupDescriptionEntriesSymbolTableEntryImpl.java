/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;

public class ReportGroupDescriptionEntriesSymbolTableEntryImpl
		implements ReportGroupDescriptionEntriesSymbolTableEntry {

	protected final static Logger LOG = LogManager.getLogger(ReportGroupDescriptionEntriesSymbolTableEntryImpl.class);

	protected List<ReportGroupDescriptionEntry> reportGroupDescriptionEntries = new ArrayList<ReportGroupDescriptionEntry>();

	@Override
	public void addReportGroupDescriptionEntry(final ReportGroupDescriptionEntry reportGroupDescriptionEntry) {
		if (!reportGroupDescriptionEntries.isEmpty()) {
			LOG.debug("multiple declarations of report group description entry {}", reportGroupDescriptionEntry);
		}

		reportGroupDescriptionEntries.add(reportGroupDescriptionEntry);
	}

	@Override
	public List<ReportGroupDescriptionEntry> getReportGroupDescriptionEntries() {
		return reportGroupDescriptionEntries;
	}

	@Override
	public ReportGroupDescriptionEntry getReportGroupDescriptionEntry() {
		return reportGroupDescriptionEntries.isEmpty() ? null : reportGroupDescriptionEntries.get(0);
	}
}
