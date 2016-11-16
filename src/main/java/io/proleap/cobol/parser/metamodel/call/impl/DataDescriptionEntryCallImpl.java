/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;

public class DataDescriptionEntryCallImpl extends CallImpl implements DataDescriptionEntryCall {

	protected DataDescriptionEntry dataDescriptionEntry;

	public DataDescriptionEntryCallImpl(final String name, final DataDescriptionEntry dataDescriptionEntry,
			final ProgramUnit programUnit, final ParseTree ctx) {
		super(name, programUnit, ctx);

		this.dataDescriptionEntry = dataDescriptionEntry;
	}

	@Override
	public CallType getCallType() {
		return CallType.DataDescriptionsEntryCall;
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
