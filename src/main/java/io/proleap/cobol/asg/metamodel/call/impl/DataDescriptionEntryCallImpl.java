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
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;

public class DataDescriptionEntryCallImpl extends CallImpl implements DataDescriptionEntryCall {

	protected final CallType callType = CallType.DATA_DESCRIPTION_ENTRY_CALL;

	protected DataDescriptionEntry dataDescriptionEntry;

	public DataDescriptionEntryCallImpl(final String name, final DataDescriptionEntry dataDescriptionEntry,
			final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.dataDescriptionEntry = dataDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry() {
		return dataDescriptionEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", dataDescriptionEntry=[" + dataDescriptionEntry + "]";
	}
}
