/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ValueOfClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ValueOfClauseImpl extends CobolDivisionElementImpl implements ValueOfClause {

	protected final ValueOfClauseContext ctx;

	protected final List<ValueOfNameValuePair> valuePairs = new ArrayList<ValueOfNameValuePair>();

	public ValueOfClauseImpl(final ProgramUnit programUnit, final ValueOfClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addValuePair(final ValueOfNameValuePair valuePair) {
		valuePairs.add(valuePair);
	}

	@Override
	public List<ValueOfNameValuePair> getValuePairs() {
		return valuePairs;
	}

}
