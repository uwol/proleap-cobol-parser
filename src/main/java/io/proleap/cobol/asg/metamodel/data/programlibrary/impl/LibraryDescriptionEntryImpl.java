/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class LibraryDescriptionEntryImpl extends CobolDivisionElementImpl implements LibraryDescriptionEntry {

	protected final String name;

	public LibraryDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
