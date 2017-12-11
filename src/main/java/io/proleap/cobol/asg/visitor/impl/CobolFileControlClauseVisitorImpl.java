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
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for collecting file control clauses in the AST.
 */
public class CobolFileControlClauseVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolFileControlClauseVisitorImpl(final Program program) {
		super(program);
	}

	protected FileControlEntry findFileControlEntry(final ParseTree ctx) {
		return ANTLRUtils.findParent(FileControlEntry.class, ctx, program.getASGElementRegistry());
	}

	@Override
	public Boolean visitAccessModeClause(final Cobol85Parser.AccessModeClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addAccessModeClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAlternateRecordKeyClause(final Cobol85Parser.AlternateRecordKeyClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addAlternateRecordKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAssignClause(final Cobol85Parser.AssignClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addAssignClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFileStatusClause(final Cobol85Parser.FileStatusClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addFileStatusClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOrganizationClause(final Cobol85Parser.OrganizationClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addOrganizationClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPaddingCharacterClause(final Cobol85Parser.PaddingCharacterClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addPaddingCharacterClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPasswordClause(final Cobol85Parser.PasswordClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addPasswordClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordDelimiterClause(final Cobol85Parser.RecordDelimiterClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addRecordDelimiterClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordKeyClause(final Cobol85Parser.RecordKeyClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addRecordKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRelativeKeyClause(final Cobol85Parser.RelativeKeyClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addRelativeKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReserveClause(final Cobol85Parser.ReserveClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addReserveClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSelectClause(final Cobol85Parser.SelectClauseContext ctx) {
		final FileControlEntry fileControlEntry = findFileControlEntry(ctx);

		fileControlEntry.addSelectClause(ctx);

		return visitChildren(ctx);
	}
}
