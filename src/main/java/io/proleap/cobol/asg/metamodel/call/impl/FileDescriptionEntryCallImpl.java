/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.FileDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;

public class FileDescriptionEntryCallImpl extends CallImpl implements FileDescriptionEntryCall {

	protected final CallType callType = CallType.FILE_DESCRIPTION_ENTRY_CALL;

	protected FileDescriptionEntry fileDescriptionEntry;

	public FileDescriptionEntryCallImpl(final String name, final FileDescriptionEntry fileDescriptionEntry,
			final ProgramUnit programUnit, final ParseTree ctx) {
		super(name, programUnit, ctx);

		this.fileDescriptionEntry = fileDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public FileDescriptionEntry getFileDescriptionEntry() {
		return fileDescriptionEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", fileDescriptionEntry=[" + fileDescriptionEntry + "]";
	}
}
