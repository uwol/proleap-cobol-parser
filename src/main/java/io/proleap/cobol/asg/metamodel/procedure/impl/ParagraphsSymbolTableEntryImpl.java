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

import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphsSymbolTableEntry;

public class ParagraphsSymbolTableEntryImpl implements ParagraphsSymbolTableEntry {

	private final static Logger LOG = LoggerFactory.getLogger(ParagraphsSymbolTableEntryImpl.class);

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
