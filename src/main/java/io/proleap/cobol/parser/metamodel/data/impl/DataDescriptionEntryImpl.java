/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public abstract class DataDescriptionEntryImpl extends CobolDivisionElementImpl implements DataDescriptionEntry {

	protected final List<DataDescriptionEntryCall> dataDescriptionEntryCalls = new ArrayList<DataDescriptionEntryCall>();

	protected DataDescriptionEntryGroup dataDescriptionEntryGroup;

	protected Integer levelNumber;

	protected final String name;

	public DataDescriptionEntryImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final ParseTree ctx) {
		super(programUnit, scope, ctx);

		this.name = name;
	}

	@Override
	public void addDataDescriptionEntryCall(final DataDescriptionEntryCall dataDescriptionEntryCall) {
		dataDescriptionEntryCalls.add(dataDescriptionEntryCall);
	}

	@Override
	public List<DataDescriptionEntryCall> getDataDescriptionEntryCalls() {
		return dataDescriptionEntryCalls;
	}

	@Override
	public DataDescriptionEntryGroup getDataDescriptionEntryGroup() {
		return dataDescriptionEntryGroup;
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
	public void setDataDescriptionEntryGroup(final DataDescriptionEntryGroup dataDescriptionEntryGroup) {
		this.dataDescriptionEntryGroup = dataDescriptionEntryGroup;
	}

	@Override
	public void setLevelNumber(final Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
