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

import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.DataRenamesClauseContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryRename;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;

public class DataDescriptionEntryRenameImpl extends DataDescriptionEntryImpl implements DataDescriptionEntryRename {

	protected final DataDescriptionEntryFormat2Context ctx;

	protected RenamesClause renamesClause;

	public DataDescriptionEntryRenameImpl(final String name,
			final DataDescriptionEntryContainer dataDescriptionEntryContainer, final ProgramUnit programUnit,
			final DataDescriptionEntryFormat2Context ctx) {
		super(name, dataDescriptionEntryContainer, programUnit, ctx);

		this.ctx = ctx;
	}

	protected List<Call> addCallsThrough(final Call firstCall, final Call lastCall,
			final DataRenamesClauseContext ctx) {
		final List<Call> result = new ArrayList<Call>();

		final boolean callTypedResolved = CallType.DATA_DESCRIPTION_ENTRY_CALL.equals(firstCall.getCallType())
				&& CallType.DATA_DESCRIPTION_ENTRY_CALL.equals(lastCall.getCallType());

		if (callTypedResolved) {
			final DataDescriptionEntryCall dataDescriptionEntryFirstCall = (DataDescriptionEntryCall) firstCall
					.unwrap();
			final DataDescriptionEntryCall dataDescriptionEntryLastCall = (DataDescriptionEntryCall) lastCall.unwrap();

			final DataDescriptionEntry firstDataDescriptionEntry = dataDescriptionEntryFirstCall
					.getDataDescriptionEntry();
			final DataDescriptionEntry lastDataDescriptionEntry = dataDescriptionEntryLastCall
					.getDataDescriptionEntry();

			final DataDescriptionEntryGroup firstParentDataDescriptionEntryGroup = firstDataDescriptionEntry
					.getParentDataDescriptionEntryGroup();
			final DataDescriptionEntryGroup lastParentDataDescriptionEntryGroup = lastDataDescriptionEntry
					.getParentDataDescriptionEntryGroup();

			final boolean isSameParentDataDescriptionEntryGroup = firstParentDataDescriptionEntryGroup == lastParentDataDescriptionEntryGroup;

			if (isSameParentDataDescriptionEntryGroup) {
				boolean inThrough = false;

				for (final DataDescriptionEntry dataDescriptionEntry : firstParentDataDescriptionEntryGroup
						.getDataDescriptionEntries()) {
					final String name = dataDescriptionEntry.getName();

					if (dataDescriptionEntry == lastDataDescriptionEntry) {
						break;
					} else if (dataDescriptionEntry == firstDataDescriptionEntry) {
						inThrough = true;
					} else if (inThrough) {
						final DataDescriptionEntryCall call = new DataDescriptionEntryCallImpl(name,
								dataDescriptionEntry, programUnit, ctx);
						result.add(call);

						linkDataDescriptionEntryCallWithDataDescriptionEntry(call, dataDescriptionEntry);
					}
				}
			}
		}

		return result;
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
			result.addCall(fromCall);

			/*
			 * to
			 */
			if (ctx.qualifiedDataName().size() > 1) {
				final QualifiedDataNameContext toContext = ctx.qualifiedDataName(1);
				final Call toCall = createCall(toContext);
				final List<Call> callsThrough = addCallsThrough(fromCall, toCall, ctx);

				result.setTo(toCall);
				result.addCalls(callsThrough);
				result.addCall(toCall);
			}

			renamesClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntryType getDataDescriptionEntryType() {
		return DataDescriptionEntryType.RENAME;
	}

	@Override
	public RenamesClause getRenamesClause() {
		return renamesClause;
	}
}
