/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface LabelRecordsClause extends CobolDivisionElement {

	enum Type {
		DataNames, Omitted, Standard
	}

	void addDataCall(Call dataCall);

	List<Call> getDataCalls();

	Type getType();

	void setType(Type type);
}
