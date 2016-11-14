/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.registry.ASGElementRegistry;
import io.proleap.cobol.parser.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolEnvironmentDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected EnvironmentDivision findEnvironmentDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(EnvironmentDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitAccessModeClause(@NotNull final Cobol85Parser.AccessModeClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addAccessModeClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAlternateRecordKeyClause(@NotNull final Cobol85Parser.AlternateRecordKeyClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addAlternateRecordKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitAssignClause(@NotNull final Cobol85Parser.AssignClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addAssignClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCharacterSetClause(@NotNull final Cobol85Parser.CharacterSetClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addCharacterSetClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCollatingSequenceClause(@NotNull final Cobol85Parser.CollatingSequenceClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addCollatingSequenceClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitConfigurationSection(@NotNull final Cobol85Parser.ConfigurationSectionContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addConfigurationSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDiskSizeClause(@NotNull final Cobol85Parser.DiskSizeClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addDiskSizeClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFileControlEntry(@NotNull final Cobol85Parser.FileControlEntryContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addFileControlEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFileControlParagraph(@NotNull final Cobol85Parser.FileControlParagraphContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addFileControlParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFileStatusClause(@NotNull final Cobol85Parser.FileStatusClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addFileStatusClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInputOutputSection(@NotNull final Cobol85Parser.InputOutputSectionContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addInputOutputSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIoControlParagraph(@NotNull final Cobol85Parser.IoControlParagraphContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addIoControlParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMemorySizeClause(@NotNull final Cobol85Parser.MemorySizeClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addMemorySizeClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitObjectComputerParagraph(@NotNull final Cobol85Parser.ObjectComputerParagraphContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addObjectComputerParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOrganizationClause(@NotNull final Cobol85Parser.OrganizationClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addOrganizationClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPaddingCharacterClause(@NotNull final Cobol85Parser.PaddingCharacterClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addPaddingCharacterClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPasswordClause(@NotNull final Cobol85Parser.PasswordClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addPasswordClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordDelimiterClause(@NotNull final Cobol85Parser.RecordDelimiterClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addRecordDelimiterClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRecordKeyClause(@NotNull final Cobol85Parser.RecordKeyClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addRecordKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRelativeKeyClause(@NotNull final Cobol85Parser.RelativeKeyClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addRelativeKeyClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReserveClause(@NotNull final Cobol85Parser.ReserveClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addReserveClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSegmentLimitClause(@NotNull final Cobol85Parser.SegmentLimitClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addSegmentLimitClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSelectClause(@NotNull final Cobol85Parser.SelectClauseContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addSelectClause(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSourceComputerParagraph(@NotNull final Cobol85Parser.SourceComputerParagraphContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addSourceComputerParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSpecialNamesParagraph(@NotNull final Cobol85Parser.SpecialNamesParagraphContext ctx) {
		final EnvironmentDivision environmentDivision = findEnvironmentDivision(ctx);

		environmentDivision.addSpecialNamesParagraph(ctx);

		return visitChildren(ctx);
	}

}
