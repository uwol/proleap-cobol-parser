/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.inspect.impl;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectConvertingPhraseContext;
import io.proleap.cobol.Cobol85Parser.InspectForContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingCharactersContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingPhraseContext;
import io.proleap.cobol.Cobol85Parser.InspectStatementContext;
import io.proleap.cobol.Cobol85Parser.InspectTallyingPhraseContext;
import io.proleap.cobol.Cobol85Parser.InspectTallyingReplacingPhraseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Converting;
import io.proleap.cobol.parser.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Tallying;
import io.proleap.cobol.parser.metamodel.procedure.inspect.TallyingReplacing;

public class InspectStatementImpl extends StatementImpl implements InspectStatement {

	protected Converting converting;

	protected final InspectStatementContext ctx;

	protected Call dataItemCall;

	protected Replacing replacing;

	protected Tallying tallying;

	protected TallyingReplacing tallyingReplacing;

	protected Type type;

	public InspectStatementImpl(final ProgramUnit programUnit, final InspectStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Converting addConverting(final InspectConvertingPhraseContext ctx) {
		Converting result = (Converting) getASGElement(ctx);

		if (result == null) {
			result = new ConvertingImpl(programUnit, ctx);

			// from
			final Call fromCall = createCall(ctx.identifier(), ctx.literal());
			result.setFromCall(fromCall);

			// to
			result.addTo(ctx.inspectTo());

			// before / after
			for (final InspectBeforeAfterContext inspectBeforeAfterPhraseContext : ctx.inspectBeforeAfter()) {
				result.addBeforeAfter(inspectBeforeAfterPhraseContext);
			}

			converting = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Replacing addReplacing(final InspectReplacingPhraseContext ctx) {
		Replacing result = (Replacing) getASGElement(ctx);

		if (result == null) {
			result = new ReplacingImpl(programUnit, ctx);

			// characters
			for (final InspectReplacingCharactersContext inspectReplacingCharactersContext : ctx
					.inspectReplacingCharacters()) {
				result.addCharacters(inspectReplacingCharactersContext);
			}

			// replacing all leadings
			for (final InspectReplacingAllLeadingsContext inspectReplacingAllLeadingsContext : ctx
					.inspectReplacingAllLeadings()) {
				result.addAllLeadings(inspectReplacingAllLeadingsContext);
			}

			replacing = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Tallying addTallying(final InspectTallyingPhraseContext ctx) {
		Tallying result = (Tallying) getASGElement(ctx);

		if (result == null) {
			result = new TallyingImpl(programUnit, ctx);

			// for
			for (final InspectForContext inspectForContext : ctx.inspectFor()) {
				result.addFor(inspectForContext);
			}

			tallying = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public TallyingReplacing addTallyingReplacing(final InspectTallyingReplacingPhraseContext ctx) {
		TallyingReplacing result = (TallyingReplacing) getASGElement(ctx);

		if (result == null) {
			result = new TallyingReplacingImpl(programUnit, ctx);

			// fors
			for (final InspectForContext inspectTallyingForContext : ctx.inspectFor()) {
				result.addFor(inspectTallyingForContext);
			}

			// replacings
			for (final InspectReplacingPhraseContext inspectReplacingPhraseContext : ctx.inspectReplacingPhrase()) {
				result.addReplacing(inspectReplacingPhraseContext);
			}

			tallyingReplacing = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Converting getConverting() {
		return converting;
	}

	@Override
	public Call getDataItemCall() {
		return dataItemCall;
	}

	@Override
	public Replacing getReplacing() {
		return replacing;
	}

	@Override
	public Tallying getTallying() {
		return tallying;
	}

	@Override
	public TallyingReplacing getTallyingReplacing() {
		return tallyingReplacing;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setDataItemCall(final Call dataItemCall) {
		this.dataItemCall = dataItemCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
