/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.impl.DataDescriptionEntryContainerImpl;

public class FileDescriptionEntryImpl extends DataDescriptionEntryContainerImpl implements FileDescriptionEntry {

	protected final FileDescriptionEntryContext ctx;

	protected final String name;

	public FileDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final FileDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
