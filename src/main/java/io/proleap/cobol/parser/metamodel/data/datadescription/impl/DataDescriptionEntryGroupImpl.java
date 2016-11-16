/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataExternalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.datadescription.ExternalClause;

//FIXME: add clauses
public class DataDescriptionEntryGroupImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryGroup {

	protected Boolean aligned;

	protected Boolean blankWhenZero;

	protected final DataDescriptionEntryFormat1Context ctx;

	protected List<DataDescriptionEntry> dataDescriptionEntries = new ArrayList<DataDescriptionEntry>();

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	protected ExternalClause externalClause;

	protected Invariance invariance;

	protected String pictureString;

	public DataDescriptionEntryGroupImpl(final String name, final ProgramUnit programUnit,
			final DataDescriptionEntryFormat1Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addDataDescriptionEntry(final DataDescriptionEntry dataDescriptionEntry) {
		final String name = dataDescriptionEntry.getName();

		dataDescriptionEntries.add(dataDescriptionEntry);
		dataDescriptionEntriesByName.put(name, dataDescriptionEntry);
	}

	@Override
	public ExternalClause addExternalClause(final DataExternalClauseContext ctx) {
		ExternalClause result = (ExternalClause) getASGElement(ctx);

		if (result == null) {
			result = new ExternalClauseImpl(programUnit, ctx);

			externalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Boolean getAligned() {
		return aligned;
	}

	@Override
	public Boolean getBlankWhenZero() {
		return blankWhenZero;
	}

	@Override
	public List<DataDescriptionEntry> getDataDescriptionEntries() {
		return dataDescriptionEntries;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry(final String name) {
		return dataDescriptionEntriesByName.get(name);
	}

	@Override
	public ExternalClause getExternalClause() {
		return externalClause;
	}

	@Override
	public Invariance getInvariance() {
		return invariance;
	}

	@Override
	public String getPictureString() {
		return pictureString;
	}

	@Override
	public Type getType() {
		final Type result;

		if (DataDescriptionEntry.LEVEL_NUMBER_SCALAR == levelNumber) {
			result = Type.Scalar;
		} else {
			result = Type.Group;
		}

		return result;
	}

	@Override
	public void setAligned(final Boolean aligned) {
		this.aligned = aligned;
	}

	@Override
	public void setBlankWhenZero(final Boolean blankWhenZero) {
		this.blankWhenZero = blankWhenZero;
	}

	@Override
	public void setInvariance(final Invariance invariance) {
		this.invariance = invariance;
	}

	@Override
	public void setPictureString(final String pictureString) {
		this.pictureString = pictureString;
	}

}
