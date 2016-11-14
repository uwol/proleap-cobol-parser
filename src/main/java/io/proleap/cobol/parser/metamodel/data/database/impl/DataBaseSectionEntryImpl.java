/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.database.impl;

import io.proleap.cobol.Cobol85Parser.DataBaseSectionEntryContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.database.DataBaseSectionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class DataBaseSectionEntryImpl extends CobolDivisionElementImpl implements DataBaseSectionEntry {

	protected final DataBaseSectionEntryContext ctx;

	protected IntegerLiteral integerLiteral;

	protected ValueStmt valueStmt1;

	protected ValueStmt valueStmt2;

	public DataBaseSectionEntryImpl(final ProgramUnit programUnit, final DataBaseSectionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public IntegerLiteral getIntegerLiteral() {
		return integerLiteral;
	}

	@Override
	public ValueStmt getValueStmt1() {
		return valueStmt1;
	}

	@Override
	public ValueStmt getValueStmt2() {
		return valueStmt2;
	}

	@Override
	public void setIntegerLiteral(final IntegerLiteral integerLiteral) {
		this.integerLiteral = integerLiteral;
	}

	@Override
	public void setValueStmt1(final ValueStmt valueStmt1) {
		this.valueStmt1 = valueStmt1;
	}

	@Override
	public void setValueStmt2(final ValueStmt valueStmt2) {
		this.valueStmt2 = valueStmt2;
	}

}
