/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.CobolParser.InspectConvertingPhraseContext;
import io.proleap.cobol.CobolParser.InspectForContext;
import io.proleap.cobol.CobolParser.InspectReplacingAllLeadingsContext;
import io.proleap.cobol.CobolParser.InspectReplacingCharactersContext;
import io.proleap.cobol.CobolParser.InspectReplacingPhraseContext;
import io.proleap.cobol.CobolParser.InspectStatementContext;
import io.proleap.cobol.CobolParser.InspectTallyingPhraseContext;
import io.proleap.cobol.CobolParser.InspectTallyingReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Converting;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Tallying;
import io.proleap.cobol.asg.metamodel.procedure.inspect.TallyingReplacing;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class InspectStatementImpl extends StatementImpl implements InspectStatement {

	protected Converting converting;

	protected final InspectStatementContext ctx;

	protected Call dataItemCall;

	protected InspectType inspectType;

	protected Replacing replacing;

	protected final StatementType statementType = StatementTypeEnum.INSPECT;

	protected Tallying tallying;

	protected TallyingReplacing tallyingReplacing;

	public InspectStatementImpl(final ProgramUnit programUnit, final Scope scope, final InspectStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Converting addConverting(final InspectConvertingPhraseContext ctx) {
		Converting result = (Converting) getASGElement(ctx);

		if (result == null) {
			result = new ConvertingImpl(programUnit, ctx);

			// from
			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setFromValueStmt(fromValueStmt);

			// to
			result.addTo(ctx.inspectTo());

			// before / after
			for (final InspectBeforeAfterContext inspectBeforeAfterPhraseContext : ctx.inspectBeforeAfter()) {
				result.addBeforeAfterPhrase(inspectBeforeAfterPhraseContext);
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
	public InspectType getInspectType() {
		return inspectType;
	}

	@Override
	public Replacing getReplacing() {
		return replacing;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
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
	public void setDataItemCall(final Call dataItemCall) {
		this.dataItemCall = dataItemCall;
	}

	@Override
	public void setInspectType(final InspectType inspectType) {
		this.inspectType = inspectType;
	}

}
