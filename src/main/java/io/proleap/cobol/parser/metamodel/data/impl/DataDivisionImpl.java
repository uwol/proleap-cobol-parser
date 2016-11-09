/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.impl.CobolScopedElementImpl;

public class DataDivisionImpl extends CobolScopedElementImpl implements DataDivision {

	protected final DataDivisionContext ctx;

	protected DataDivisionBody dataDivisionBody;

	public DataDivisionImpl(final CopyBook copyBook, final CobolScope superScope, final DataDivisionContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
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
