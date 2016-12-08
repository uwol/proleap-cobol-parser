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
import io.proleap.cobol.asg.metamodel.call.ScreenDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;

public class ScreenDescriptionEntryCallImpl extends CallImpl implements ScreenDescriptionEntryCall {

	protected final CallType callType = CallType.ScreenDescriptionEntryCall;

	protected ScreenDescriptionEntry screenDescriptionEntry;

	public ScreenDescriptionEntryCallImpl(final String name, final ScreenDescriptionEntry screenDescriptionEntry,
			final ProgramUnit programUnit, final ParseTree ctx) {
		super(name, programUnit, ctx);

		this.screenDescriptionEntry = screenDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public ScreenDescriptionEntry getScreenDescriptionEntry() {
		return screenDescriptionEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", screenDescriptionEntry=[" + screenDescriptionEntry + "]";
	}
}
