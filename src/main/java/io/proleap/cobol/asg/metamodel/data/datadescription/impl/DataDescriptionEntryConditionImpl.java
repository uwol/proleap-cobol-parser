/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.DataValueClauseContext;
import io.proleap.cobol.CobolParser.DataValueIntervalContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryCondition;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueClause;

public class DataDescriptionEntryConditionImpl extends DataDescriptionEntryImpl
		implements DataDescriptionEntryCondition {

	protected final DataDescriptionEntryFormat3Context ctx;

	protected ValueClause valueClause;

	public DataDescriptionEntryConditionImpl(final String name,
			final DataDescriptionEntryContainer dataDescriptionEntryContainer, final ProgramUnit programUnit,
			final DataDescriptionEntryFormat3Context ctx) {
		super(name, dataDescriptionEntryContainer, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueClause addValueClause(final DataValueClauseContext ctx) {
		ValueClause result = (ValueClause) getASGElement(ctx);

		if (result == null) {
			result = new ValueClauseImpl(programUnit, ctx);

			for (final DataValueIntervalContext dataValueIntervalContext : ctx.dataValueInterval()) {
				result.addValueInterval(dataValueIntervalContext);
			}

			valueClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryType getDataDescriptionEntryType() {
		return DataDescriptionEntryType.CONDITION;
	}

	@Override
	public ValueClause getValueClause() {
		return valueClause;
	}
}
