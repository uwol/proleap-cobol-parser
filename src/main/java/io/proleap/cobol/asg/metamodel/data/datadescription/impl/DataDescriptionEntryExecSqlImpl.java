/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataDescriptionEntryExecSqlContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryExecSql;

public class DataDescriptionEntryExecSqlImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryExecSql {

	protected final DataDescriptionEntryExecSqlContext ctx;

	protected String execSqlText;

	public DataDescriptionEntryExecSqlImpl(final DataDescriptionEntryContainer dataDescriptionEntryContainer,
			final ProgramUnit programUnit, final DataDescriptionEntryExecSqlContext ctx) {
		super(null, dataDescriptionEntryContainer, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DataDescriptionEntryType getDataDescriptionEntryType() {
		return DataDescriptionEntryType.EXEC_SQL;
	}

	@Override
	public String getExecSqlText() {
		return execSqlText;
	}

	@Override
	public void setExecSqlText(final String execSqlText) {
		this.execSqlText = execSqlText;
	}
}
