/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.CobolParser;
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
	public Boolean visitBlockContainsClause(final CobolParser.BlockContainsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addBlockContainsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCodeSetClause(final CobolParser.CodeSetClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addCodeSetClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataRecordsClause(final CobolParser.DataRecordsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addDataRecordsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExternalClause(final CobolParser.ExternalClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addExternalClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitGlobalClause(final CobolParser.GlobalClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addGlobalClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLabelRecordsClause(final CobolParser.LabelRecordsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addLabelRecordsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLinageClause(final CobolParser.LinageClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addLinageClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordContainsClause(final CobolParser.RecordContainsClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addRecordContainsClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReportClause(final CobolParser.ReportClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addReportClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitValueOfClause(final CobolParser.ValueOfClauseContext ctx) {
		final FileDescriptionEntry fileDescriptionEntry = findFileDescriptionEntry(ctx);

		fileDescriptionEntry.addValueOfClause(ctx);

		return visitChildren(ctx);
	}
}
