/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ValueOfClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
