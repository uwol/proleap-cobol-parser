/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.CommunicationSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;

public class CommunicationSectionImpl extends DataDescriptionEntryContainerImpl implements CommunicationSection {

	private final static Logger LOG = LogManager.getLogger(CommunicationSectionImpl.class);

	protected List<CommunicationDescriptionEntry> communicationDescriptionEntries = new ArrayList<CommunicationDescriptionEntry>();

	protected Map<String, CommunicationDescriptionEntry> communicationDescriptionEntriesByName = new HashMap<String, CommunicationDescriptionEntry>();

	protected final CommunicationSectionContext ctx;

	public CommunicationSectionImpl(final ProgramUnit programUnit, final CommunicationSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommunicationDescriptionEntryInput addCommunicationDescriptionEntryInput(
			final CommunicationDescriptionEntryFormat1Context ctx) {
		CommunicationDescriptionEntryInput result = (CommunicationDescriptionEntryInput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new CommunicationDescriptionEntryInputImpl(name, programUnit, ctx);

			// FIXME

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntryInputOutput addCommunicationDescriptionEntryInputOutput(
			final CommunicationDescriptionEntryFormat3Context ctx) {
		CommunicationDescriptionEntryInputOutput result = (CommunicationDescriptionEntryInputOutput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new CommunicationDescriptionEntryInputOutputImpl(name, programUnit, ctx);

			// FIXME

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntryOutput addCommunicationDescriptionEntryOutput(
			final CommunicationDescriptionEntryFormat2Context ctx) {
		CommunicationDescriptionEntryOutput result = (CommunicationDescriptionEntryOutput) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new CommunicationDescriptionEntryOutputImpl(name, programUnit, ctx);

			// FIXME

			communicationDescriptionEntries.add(result);
			communicationDescriptionEntriesByName.put(name, result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommunicationDescriptionEntry createCommunicationDescriptionEntry(
			final CommunicationDescriptionEntryContext communicationDescriptionEntryContext) {
		final CommunicationDescriptionEntry result;

		if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat1() != null) {
			result = addCommunicationDescriptionEntryInput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat1());
		} else if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat2() != null) {
			result = addCommunicationDescriptionEntryOutput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat2());
		} else if (communicationDescriptionEntryContext.communicationDescriptionEntryFormat3() != null) {
			result = addCommunicationDescriptionEntryInputOutput(
					communicationDescriptionEntryContext.communicationDescriptionEntryFormat3());
		} else {
			LOG.warn("unknown communication description entry {}", communicationDescriptionEntryContext);
			result = null;
		}

		return result;
	}

	@Override
	public List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries() {
		return communicationDescriptionEntries;
	}

	@Override
	public CommunicationDescriptionEntry getCommunicationDescriptionEntry(final String name) {
		return communicationDescriptionEntriesByName.get(name);
	}
}
