/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class CommunicationDescriptionEntryImpl extends CobolDivisionElementImpl
		implements CommunicationDescriptionEntry {

	protected final List<CommunicationDescriptionEntryCall> calls = new ArrayList<CommunicationDescriptionEntryCall>();

	protected final String name;

	public CommunicationDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParserRuleContext ctx) {
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
