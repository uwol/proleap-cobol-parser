/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry1;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry2;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry3;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

public class DataDivisionImpl extends CobolDivisionImpl implements DataDivision {

	private final static Logger LOG = LogManager.getLogger(DataDivisionImpl.class);

	protected final DataDivisionContext ctx;

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	protected DataDivisionBody dataDivisionBody;

	public DataDivisionImpl(final ProgramUnit programUnit, final DataDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat1Context ctx) {
		DataDescriptionEntry1 result = (DataDescriptionEntry1) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry1Impl(name, programUnit, this, ctx);

			registerASGElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat2Context ctx) {
		DataDescriptionEntry2 result = (DataDescriptionEntry2) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry2Impl(name, programUnit, this, ctx);

			registerASGElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat3Context ctx) {
		DataDescriptionEntry3 result = (DataDescriptionEntry3) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry3Impl(name, programUnit, this, ctx);

			registerASGElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDivisionBody addDataDivisionBody(final DataDivisionBodyContext ctx) {
		DataDivisionBody result = (DataDivisionBody) getASGElement(ctx);

		if (result == null) {
			result = new DataDivisionBodyImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry(final String name) {
		return dataDescriptionEntriesByName.get(name);
	}

	@Override
	public DataDivisionBody getDataDivisionBody() {
		return dataDivisionBody;
	}

	@Override
	public void setDataDivisionBody(final DataDivisionBody dataDivisionBody) {
		this.dataDivisionBody = dataDivisionBody;
	}
}
