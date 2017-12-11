/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class DataDescriptionEntryImpl extends CobolDivisionElementImpl implements DataDescriptionEntry {

	protected final List<DataDescriptionEntryCall> calls = new ArrayList<DataDescriptionEntryCall>();

	protected DataDescriptionEntryContainer dataDescriptionEntryContainer;

	protected DataDescriptionEntry dataDescriptionEntryPredecessor;

	protected DataDescriptionEntry dataDescriptionEntrySuccessor;

	protected Integer levelNumber;

	protected final String name;

	protected DataDescriptionEntryGroup parentDataDescriptionEntryGroup;

	public DataDescriptionEntryImpl(final String name,
			final DataDescriptionEntryContainer dataDescriptionEntryContainer, final ProgramUnit programUnit,
			final ParserRuleContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.dataDescriptionEntryContainer = dataDescriptionEntryContainer;
	}

	@Override
	public void addCall(final DataDescriptionEntryCall call) {
		calls.add(call);
	}

	@Override
	public List<DataDescriptionEntryCall> getCalls() {
		return calls;
	}

	@Override
	public DataDescriptionEntryContainer getDataDescriptionEntryContainer() {
		return dataDescriptionEntryContainer;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntryPredecessor() {
		return dataDescriptionEntryPredecessor;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntrySuccessor() {
		return dataDescriptionEntrySuccessor;
	}

	@Override
	public Integer getLevelNumber() {
		return levelNumber;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DataDescriptionEntryGroup getParentDataDescriptionEntryGroup() {
		return parentDataDescriptionEntryGroup;
	}

	@Override
	public void setDataDescriptionEntryPredecessor(final DataDescriptionEntry predecessor) {
		dataDescriptionEntryPredecessor = predecessor;
	}

	@Override
	public void setDataDescriptionEntrySuccessor(final DataDescriptionEntry successor) {
		dataDescriptionEntrySuccessor = successor;
	}

	@Override
	public void setLevelNumber(final Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Override
	public void setParentDataDescriptionEntryGroup(final DataDescriptionEntryGroup parentDataDescriptionEntryGroup) {
		this.parentDataDescriptionEntryGroup = parentDataDescriptionEntryGroup;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
