/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataExternalClauseContext;

public interface DataDescriptionEntryGroup extends DataDescriptionEntry {

	public enum Invariance {
		Common, Local, Own
	}

	void addDataDescriptionEntry(DataDescriptionEntry dataDescriptionEntry);

	ExternalClause addExternalClause(DataExternalClauseContext ctx);

	Boolean getAligned();

	Boolean getBlankWhenZero();

	List<DataDescriptionEntry> getDataDescriptionEntries();

	DataDescriptionEntry getDataDescriptionEntry(String name);

	ExternalClause getExternalClause();

	Invariance getInvariance();

	String getPictureString();

	void setAligned(Boolean aligned);

	void setBlankWhenZero(Boolean blankWhenZero);

	void setInvariance(Invariance invariance);

	void setPictureString(String pictureString);
}
