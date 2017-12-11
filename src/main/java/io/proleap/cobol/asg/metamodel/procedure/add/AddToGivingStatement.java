/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

/**
 * Adds one or multiple values to one or multiple variables and stores the
 * result in separate variables.
 */
public interface AddToGivingStatement extends CobolDivisionElement {

	void addFrom(From from);

	void addGiving(Giving giving);

	void addTo(ToGiving to);

	List<From> getFroms();

	List<Giving> getGivings();

	List<ToGiving> getTos();
}
