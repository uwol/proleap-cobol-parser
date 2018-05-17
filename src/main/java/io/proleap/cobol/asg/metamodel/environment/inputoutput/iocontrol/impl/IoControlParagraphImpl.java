/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.CommitmentControlClauseContext;
import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.IoControlParagraphContext;
import io.proleap.cobol.CobolParser.MultipleFileClauseContext;
import io.proleap.cobol.CobolParser.MultipleFilePositionContext;
import io.proleap.cobol.CobolParser.RerunClauseContext;
import io.proleap.cobol.CobolParser.SameClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.CommitmentControlClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFileClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class IoControlParagraphImpl extends CobolDivisionElementImpl implements IoControlParagraph {

	protected CommitmentControlClause commitmentControlClause;

	protected final IoControlParagraphContext ctx;

	protected Call fileCall;

	protected MultipleFileClause multipleFileClause;

	protected RerunClause rerunClause;

	protected List<SameClause> sameClauses = new ArrayList<SameClause>();

	public IoControlParagraphImpl(final ProgramUnit programUnit, final IoControlParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommitmentControlClause addCommitmentControlClause(final CommitmentControlClauseContext ctx) {
		CommitmentControlClause result = (CommitmentControlClause) getASGElement(ctx);

		if (result == null) {
			result = new CommitmentControlClauseImpl(programUnit, ctx);

			if (ctx.fileName() != null) {
				final Call fileCall = createCall(ctx.fileName());
				result.setFileCall(fileCall);
			}

			commitmentControlClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MultipleFileClause addMultipleFileClause(final MultipleFileClauseContext ctx) {
		MultipleFileClause result = (MultipleFileClause) getASGElement(ctx);

		if (result == null) {
			result = new MultipleFileClauseImpl(programUnit, ctx);

			for (final MultipleFilePositionContext multipleFilePositionContext : ctx.multipleFilePosition()) {
				result.addMultipleFilePosition(multipleFilePositionContext);
			}

			multipleFileClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RerunClause addRerunClause(final RerunClauseContext ctx) {
		RerunClause result = (RerunClause) getASGElement(ctx);

		if (result == null) {
			result = new RerunClauseImpl(programUnit, ctx);

			/*
			 * on value
			 */
			final ValueStmt onValueStmt = createValueStmt(ctx.assignmentName(), ctx.fileName());
			result.setOnValueStmt(onValueStmt);

			if (ctx.rerunEveryRecords() != null) {
				result.addRerunEveryRecords(ctx.rerunEveryRecords());
			}

			if (ctx.rerunEveryOf() != null) {
				result.addRerunEveryOf(ctx.rerunEveryOf());
			}

			if (ctx.rerunEveryClock() != null) {
				result.addRerunEveryClock(ctx.rerunEveryClock());
			}

			rerunClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SameClause addSameClause(final SameClauseContext ctx) {
		SameClause result = (SameClause) getASGElement(ctx);

		if (result == null) {
			result = new SameClauseImpl(programUnit, ctx);

			/*
			 * form
			 */
			final SameClause.Form form;

			if (ctx.RECORD() != null) {
				form = SameClause.Form.RECORD;
			} else if (ctx.SORT() != null) {
				form = SameClause.Form.SORT;
			} else if (ctx.SORT_MERGE() != null) {
				form = SameClause.Form.SORT_MERGE;
			} else {
				form = null;
			}

			result.setForm(form);

			/*
			 * file names
			 */
			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			sameClauses.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CommitmentControlClause getCommitmentControlClause() {
		return commitmentControlClause;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public MultipleFileClause getMultipleFileClause() {
		return multipleFileClause;
	}

	@Override
	public RerunClause getRerunClause() {
		return rerunClause;
	}

	@Override
	public List<SameClause> getSameClauses() {
		return sameClauses;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}
}
