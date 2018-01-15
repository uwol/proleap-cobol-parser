/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;

public interface DataDescriptionEntry extends CobolDivisionElement, Declaration {

	enum DataDescriptionEntryType {
		CONDITION, EXEC_SQL, GROUP, RENAME, SCALAR
	}

	static final int LEVEL_NUMBER_CONDITION = 88;

	static final int LEVEL_NUMBER_RENAME = 66;

	static final int LEVEL_NUMBER_SCALAR = 77;

	void addCall(DataDescriptionEntryCall call);

	List<DataDescriptionEntryCall> getCalls();

	DataDescriptionEntryContainer getDataDescriptionEntryContainer();

	DataDescriptionEntry getDataDescriptionEntryPredecessor();

	DataDescriptionEntry getDataDescriptionEntrySuccessor();

	DataDescriptionEntryType getDataDescriptionEntryType();

	Integer getLevelNumber();

	DataDescriptionEntryGroup getParentDataDescriptionEntryGroup();

	void setDataDescriptionEntryPredecessor(DataDescriptionEntry predecessor);

	void setDataDescriptionEntrySuccessor(DataDescriptionEntry successor);

	void setLevelNumber(Integer levelNumber);

	void setParentDataDescriptionEntryGroup(DataDescriptionEntryGroup parentDataDescriptionEntryGroup);
}
