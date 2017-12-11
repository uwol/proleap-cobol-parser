/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface LabelRecordsClause extends CobolDivisionElement {

	enum LabelRecordsClauseType {
		DATA_NAMES, OMITTED, STANDARD
	}

	void addDataCall(Call dataCall);

	List<Call> getDataCalls();

	LabelRecordsClauseType getLabelRecordsClauseType();

	void setLabelRecordsClauseType(LabelRecordsClauseType labelRecordsClauseType);
}
