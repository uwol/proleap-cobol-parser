/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;

public interface DataDescriptionEntry extends CobolDivisionElement, Declaration {

	enum Type {
		Condition, Group, Rename, Scalar
	}

	static final int LEVEL_NUMBER_CONDITION = 88;

	static final int LEVEL_NUMBER_RENAME = 66;

	static final int LEVEL_NUMBER_SCALAR = 77;

	void addCall(DataDescriptionEntryCall call);

	List<DataDescriptionEntryCall> getCalls();

	Integer getLevelNumber();

	DataDescriptionEntryGroup getParentDataDescriptionEntryGroup();

	Type getType();

	void setLevelNumber(Integer levelNumber);

	void setParentDataDescriptionEntryGroup(DataDescriptionEntryGroup parentDataDescriptionEntryGroup);
}
