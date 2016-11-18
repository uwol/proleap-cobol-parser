/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.DataOccursSortContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.OccursSort;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class OccursSortImpl extends CobolDivisionElementImpl implements OccursSort {

	protected DataOccursSortContext ctx;

	protected List<ValueStmt> keyValueStmts = new ArrayList<ValueStmt>();

	protected Order order;

	public OccursSortImpl(final ProgramUnit programUnit, final DataOccursSortContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addKeyValueStmt(final ValueStmt keyValueStmt) {
		keyValueStmts.add(keyValueStmt);
	}

	@Override
	public List<ValueStmt> getKeyValueStmts() {
		return keyValueStmts;
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
