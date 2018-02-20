/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.SectionsSymbolTableEntry;

public class SectionsSymbolTableEntryImpl implements SectionsSymbolTableEntry {

	private final static Logger LOG = LoggerFactory.getLogger(SectionsSymbolTableEntryImpl.class);

	protected List<Section> sections = new ArrayList<Section>();

	@Override
	public void addSection(final Section section) {
		if (!sections.isEmpty()) {
			LOG.debug("multiple declarations of section {}", section);
		}

		sections.add(section);
	}

	@Override
	public Section getSection() {
		return sections.isEmpty() ? null : sections.get(0);
	}

	@Override
	public List<Section> getSections() {
		return sections;
	}
}
