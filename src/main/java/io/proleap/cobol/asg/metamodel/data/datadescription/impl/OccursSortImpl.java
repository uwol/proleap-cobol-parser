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

import io.proleap.cobol.CobolParser.DataOccursSortContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursSort;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class OccursSortImpl extends CobolDivisionElementImpl implements OccursSort {

	protected DataOccursSortContext ctx;

	protected List<Call> keyCalls = new ArrayList<Call>();

	protected Order order;

	public OccursSortImpl(final ProgramUnit programUnit, final DataOccursSortContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addKeyCall(final Call keyCall) {
		keyCalls.add(keyCall);
	}

	@Override
	public List<Call> getKeyCalls() {
		return keyCalls;
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public void setOrder(final Order order) {
		this.order = order;
	}

}
