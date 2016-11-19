/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public abstract class LibraryDescriptionEntryImpl extends CobolDivisionElementImpl implements LibraryDescriptionEntry {

	protected final String name;

	public LibraryDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
