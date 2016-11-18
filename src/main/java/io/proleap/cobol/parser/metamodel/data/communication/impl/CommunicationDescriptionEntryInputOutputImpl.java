/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;

public class CommunicationDescriptionEntryInputOutputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryInputOutput {

	protected final CommunicationDescriptionEntryFormat3Context ctx;

	public CommunicationDescriptionEntryInputOutputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat3Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return Type.InputOutput;
	}

}
