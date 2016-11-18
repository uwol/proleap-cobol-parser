/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryOutput;

public class CommunicationDescriptionEntryOutputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryOutput {

	protected final CommunicationDescriptionEntryFormat2Context ctx;

	public CommunicationDescriptionEntryOutputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return Type.Output;
	}

}
