/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.data.FileSection;
import io.proleap.cobol.parser.metamodel.data.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

//FIXME: remove
public class DataDivisionBodyImpl extends CobolDivisionElementImpl implements DataDivisionBody {

	protected FileSection fileSection;

	protected WorkingStorageSection workingStorageSection;

	public DataDivisionBodyImpl(final ProgramUnit programUnit, final CobolDivision scope, final ParseTree ctx) {
		super(programUnit, scope, ctx);
	}

	@Override
	public FileSection getFileSection() {
		return fileSection;
	}

	@Override
	public WorkingStorageSection getWorkingStorageSection() {
		return workingStorageSection;
	}

	@Override
	public void setFileSection(final FileSection fileSection) {
		this.fileSection = fileSection;
	}

	@Override
	public void setWorkingStorageSection(final WorkingStorageSection workingStorageSection) {
		this.workingStorageSection = workingStorageSection;
	}

}
