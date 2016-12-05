/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public abstract class DataDescriptionEntryImpl extends CobolDivisionElementImpl implements DataDescriptionEntry {

	protected final List<DataDescriptionEntryCall> calls = new ArrayList<DataDescriptionEntryCall>();

	protected Integer levelNumber;

	protected final String name;

	protected DataDescriptionEntryGroup parentDataDescriptionEntryGroup;

	public DataDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.name = name;
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
