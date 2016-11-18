/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class CommunicationDescriptionEntryImpl extends CobolDivisionElementImpl
		implements CommunicationDescriptionEntry {

	private final static Logger LOG = LogManager.getLogger(CommunicationDescriptionEntryImpl.class);

	protected final CommunicationDescriptionEntryContext ctx;

	protected final String name;

	public CommunicationDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final CommunicationDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
