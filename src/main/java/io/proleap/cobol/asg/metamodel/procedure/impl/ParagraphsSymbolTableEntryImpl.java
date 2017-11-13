/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphsSymbolTableEntry;

public class ParagraphsSymbolTableEntryImpl implements ParagraphsSymbolTableEntry {

	protected final static Logger LOG = LogManager.getLogger(ParagraphsSymbolTableEntryImpl.class);

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	@Override
	public void addParagraph(final Paragraph paragraph) {
		if (!paragraphs.isEmpty()) {
			LOG.debug("multiple declarations of paragraph {}", paragraph);
		}

		paragraphs.add(paragraph);
	}

	@Override
	public Paragraph getParagraph() {
		return paragraphs.isEmpty() ? null : paragraphs.get(0);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
}
