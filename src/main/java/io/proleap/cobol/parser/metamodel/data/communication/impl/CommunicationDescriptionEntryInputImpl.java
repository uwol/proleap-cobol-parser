/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInput;

public class CommunicationDescriptionEntryInputImpl extends CommunicationDescriptionEntryImpl
		implements CommunicationDescriptionEntryInput {

	protected final CommunicationDescriptionEntryFormat1Context ctx;

	public CommunicationDescriptionEntryInputImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryFormat1Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return Type.Input;
	}

}
