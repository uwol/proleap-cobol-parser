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
import io.proleap.cobol.asg.metamodel.call.ScreenDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;

public class ScreenDescriptionEntryCallImpl extends CallImpl implements ScreenDescriptionEntryCall {

	protected final CallType callType = CallType.SCREEN_DESCRIPTION_ENTRY_CALL;

	protected ScreenDescriptionEntry screenDescriptionEntry;

	public ScreenDescriptionEntryCallImpl(final String name, final ScreenDescriptionEntry screenDescriptionEntry,
			final ProgramUnit programUnit, final ParserRuleContext ctx) {
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
