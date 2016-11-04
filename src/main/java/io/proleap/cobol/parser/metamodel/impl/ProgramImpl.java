/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;

public class ProgramImpl extends CobolScopeImpl implements Program {

	protected final Map<String, CopyBook> copyBooks = new LinkedHashMap<String, CopyBook>();

	public ProgramImpl() {
		super(null, null, null);
	}

	@Override
	public CopyBook getCopyBook(final String name) {
		final String copyBookKey = getCopyBookKey(name);
		return copyBooks.get(copyBookKey);
	}

	private String getCopyBookKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public Collection<CopyBook> getCopyBooks() {
		return copyBooks.values();
	}

	@Override
	public void registerCopyBook(final CopyBook copyBook) {
		final String copyBookKey = getCopyBookKey(copyBook.getName());
		copyBooks.put(copyBookKey, copyBook);
	}
}
