/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.IndexCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class IndexImpl extends CobolDivisionElementImpl implements Index {

	protected List<IndexCall> calls = new ArrayList<IndexCall>();

	protected IndexNameContext ctx;

	protected final String name;

	public IndexImpl(final String name, final ProgramUnit programUnit, final IndexNameContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addCall(final IndexCall call) {
		calls.add(call);
	}

	@Override
	public List<IndexCall> getCalls() {
		return calls;
	}

	@Override
	public String getName() {
		return name;
	}

}
