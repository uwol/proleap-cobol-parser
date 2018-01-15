/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;

public class FileControlEntryCallImpl extends CallImpl implements FileControlEntryCall {

	protected final CallType callType = CallType.FILE_CONTROL_ENTRY_CALL;

	protected FileControlEntry fileControlEntry;

	public FileControlEntryCallImpl(final String name, final FileControlEntry fileControlEntry,
			final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.fileControlEntry = fileControlEntry;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public FileControlEntry getFileControlEntry() {
		return fileControlEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", fileControlEntry=[" + fileControlEntry + "]";
	}
}
