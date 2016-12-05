/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataRenamesClauseContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryRename;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;

public class DataDescriptionEntryRenameImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryRename {

	protected final DataDescriptionEntryFormat2Context ctx;

	protected RenamesClause renamesClause;

	public DataDescriptionEntryRenameImpl(final String name, final ProgramUnit programUnit,
			final DataDescriptionEntryFormat2Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public RenamesClause addRenamesClause(final DataRenamesClauseContext ctx) {
		RenamesClause result = (RenamesClause) getASGElement(ctx);

		if (result == null) {
			result = new RenamesClauseImpl(programUnit, ctx);

			/*
			 * from
			 */
			final QualifiedDataNameContext fromContext = ctx.qualifiedDataName(0);
			final Call fromCall = createCall(fromContext);
			result.setFrom(fromCall);

			/*
			 * to
			 */
			if (ctx.qualifiedDataName().size() > 1) {
				final QualifiedDataNameContext toContext = ctx.qualifiedDataName(1);
				final Call toCall = createCall(toContext);
				result.setTo(toCall);
			}

			renamesClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RenamesClause getRenamesClause() {
		return renamesClause;
	}

	@Override
	public Type getType() {
		return Type.Rename;
	}

}
