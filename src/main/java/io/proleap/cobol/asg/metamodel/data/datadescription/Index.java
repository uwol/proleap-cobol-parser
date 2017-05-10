/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.NamedElement;
import io.proleap.cobol.asg.metamodel.call.IndexCall;

public interface Index extends CobolDivisionElement, NamedElement {

	void addCall(IndexCall call);

	List<IndexCall> getCalls();
}
