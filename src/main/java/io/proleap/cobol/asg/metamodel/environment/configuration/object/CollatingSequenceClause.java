/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object;

import java.util.List;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface CollatingSequenceClause extends CobolDivisionElement {

	void addAlphabetName(String alphabetName);

	List<String> getAlphabetNames();

	String getAlphaNumeric();

	String getNational();

	void setAlphaNumeric(String alphanumeric);

	void setNational(String national);
}
