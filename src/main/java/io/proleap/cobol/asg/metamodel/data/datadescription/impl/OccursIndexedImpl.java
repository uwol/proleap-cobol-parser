/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.DataOccursIndexedContext;
import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursIndexed;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class OccursIndexedImpl extends CobolDivisionElementImpl implements OccursIndexed {

	protected DataOccursIndexedContext ctx;

	protected List<Index> indices = new ArrayList<Index>();

	protected Map<String, Index> indicesSymbolTable = new HashMap<String, Index>();

	public OccursIndexedImpl(final ProgramUnit programUnit, final DataOccursIndexedContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Index addIndex(final IndexNameContext ctx) {
		final Index result = (Index) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Index index = new IndexImpl(name, programUnit, ctx);

			indices.add(index);
			indicesSymbolTable.put(getSymbol(name), index);

			registerASGElement(index);
		}

		return result;
	}

	@Override
	public Index getIndex(final String name) {
		return indicesSymbolTable.get(getSymbol(name));
	}

	@Override
	public List<Index> getIndices() {
		return indices;
	}
}
