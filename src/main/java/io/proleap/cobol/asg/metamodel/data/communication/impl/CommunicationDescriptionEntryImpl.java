/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class CommunicationDescriptionEntryImpl extends CobolDivisionElementImpl
		implements CommunicationDescriptionEntry {

	protected final List<CommunicationDescriptionEntryCall> calls = new ArrayList<CommunicationDescriptionEntryCall>();

	protected final String name;

	public CommunicationDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.name = name;
	}

	@Override
	public void addCall(final CommunicationDescriptionEntryCall call) {
		calls.add(call);
	}

	@Override
	public List<CommunicationDescriptionEntryCall> getCalls() {
		return calls;
	}

	@Override
	public String getName() {
		return name;
	}

}
