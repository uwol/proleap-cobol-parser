/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CommitmentControlClauseContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.MultipleFileClauseContext;
import io.proleap.cobol.Cobol85Parser.MultipleFilePositionContext;
import io.proleap.cobol.Cobol85Parser.RerunClauseContext;
import io.proleap.cobol.Cobol85Parser.SameClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.CommitmentControlClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.MultipleFileClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.RerunClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class IoControlParagraphImpl extends CobolDivisionElementImpl implements IoControlParagraph {

	private final static Logger LOG = LogManager.getLogger(IoControlParagraphImpl.class);

	protected CommitmentControlClause commitmentControlClause;

	protected final IoControlParagraphContext ctx;

	protected ValueStmt fileNameValueStmt;

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
				final ValueStmt fileNameValueStmt = createCallValueStmt(ctx.fileName());
				result.setFileNameValueStmt(fileNameValueStmt);
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
			final ValueStmt onValueStmt;

			if (ctx.assignmentName() != null) {
				onValueStmt = createCallValueStmt(ctx.assignmentName());
			} else if (ctx.fileName() != null) {
				onValueStmt = createCallValueStmt(ctx.fileName());
			} else {
				LOG.warn("unknown on value stmt {}.", ctx);
				onValueStmt = null;
			}

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
				form = SameClause.Form.Record;
			} else if (ctx.SORT() != null) {
				form = SameClause.Form.Sort;
			} else if (ctx.SORT_MERGE() != null) {
				form = SameClause.Form.SortMerge;
			} else {
				form = null;
			}

			result.setForm(form);

			/*
			 * file names
			 */
			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final ValueStmt fileNameValueStmt = createCallValueStmt(fileNameContext);
				result.addFileNameValueStmt(fileNameValueStmt);
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
	public ValueStmt getFileNameValueStmt() {
		return fileNameValueStmt;
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
	public void setFileNameValueStmt(final ValueStmt fileNameValueStmt) {
		this.fileNameValueStmt = fileNameValueStmt;
	}

}
