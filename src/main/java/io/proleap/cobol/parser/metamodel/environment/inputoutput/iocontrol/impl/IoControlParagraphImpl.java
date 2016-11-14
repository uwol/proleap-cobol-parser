/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.CommitmentControlClauseContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.MultipleFileClauseContext;
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

	protected CommitmentControlClause commitmentControlClause;

	protected final IoControlParagraphContext ctx;

	protected ValueStmt fileNameValueStmt;

	protected MultipleFileClause multipleFileClause;

	protected RerunClause rerunClause;

	protected SameClause sameClause;

	public IoControlParagraphImpl(final ProgramUnit programUnit, final IoControlParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CommitmentControlClause addCommitmentControlClause(final CommitmentControlClauseContext ctx) {
		return null;
	}

	@Override
	public MultipleFileClause addMultipleFileClause(final MultipleFileClauseContext ctx) {
		return null;
	}

	@Override
	public RerunClause addRerunClause(final RerunClauseContext ctx) {
		return null;
	}

	@Override
	public SameClause addSameClause(final SameClauseContext ctx) {
		return null;
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
	public SameClause getSameClause() {
		return sameClause;
	}

	@Override
	public void setFileNameValueStmt(final ValueStmt fileNameValueStmt) {
		this.fileNameValueStmt = fileNameValueStmt;
	}

}
