/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface SameClause extends CobolDivisionElement {

	enum Form {
		RECORD, SORT, SORT_MERGE
	}

	void addFileCall(Call fileCall);

	List<Call> getFileCalls();

	Form getForm();

	void setForm(Form form);
}
