/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for collecting file control clauses in the AST.
 */
public class CobolFileDescriptionEntryClauseVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolFileDescriptionEntryClauseVisitorImpl(final Program program) {
		super(program);
	}

	protected FileDescriptionEntry findFileDescriptionEntry(final ParseTree ctx) {
		return ANTLRUtils.findParent(FileDescriptionEntry.class, ctx, program.getASGElementRegistry());
	}

	@Override
	public Boolean visitBlockContainsClause(final Cobol85Parser.BlockContainsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addBlockContainsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCodeSetClause(final Cobol85Parser.CodeSetClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addCodeSetClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataRecordsClause(final Cobol85Parser.DataRecordsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addDataRecordsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExternalClause(final Cobol85Parser.ExternalClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addExternalClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGlobalClause(final Cobol85Parser.GlobalClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addGlobalClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLabelRecordsClause(final Cobol85Parser.LabelRecordsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addLabelRecordsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLinageClause(final Cobol85Parser.LinageClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addLinageClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordContainsClause(final Cobol85Parser.RecordContainsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addRecordContainsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReportClause(final Cobol85Parser.ReportClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addReportClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitValueOfClause(final Cobol85Parser.ValueOfClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addValueOfClause(ctx);

		return visitChildren(ctx);
	}
}
