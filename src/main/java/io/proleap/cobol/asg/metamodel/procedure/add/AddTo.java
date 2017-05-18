/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

/**
 * Adds one or multiple values to one or multiple variables.
 */
public interface AddTo extends CobolDivisionElement {

	void addFrom(From from);

	void addTo(To to);

	List<From> getFroms();

	List<To> getTos();
}
