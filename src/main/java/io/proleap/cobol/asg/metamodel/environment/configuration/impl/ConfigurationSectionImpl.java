/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.impl;

import io.proleap.cobol.CobolParser.ConfigurationSectionContext;
import io.proleap.cobol.CobolParser.ObjectComputerClauseContext;
import io.proleap.cobol.CobolParser.ObjectComputerParagraphContext;
import io.proleap.cobol.CobolParser.SourceComputerParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.impl.ObjectComputerParagraphImpl;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.impl.SourceComputerParagraphImpl;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ConfigurationSectionImpl extends CobolDivisionElementImpl implements ConfigurationSection {

	protected final ConfigurationSectionContext ctx;

	protected ObjectComputerParagraph objectComputerParagraph;

	protected SourceComputerParagraph sourceComputerParagraph;

	public ConfigurationSectionImpl(final ProgramUnit programUnit, final ConfigurationSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ObjectComputerParagraph addObjectComputerParagraph(final ObjectComputerParagraphContext ctx) {
		ObjectComputerParagraph result = (ObjectComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ObjectComputerParagraphImpl(name, programUnit, ctx);

			for (final ObjectComputerClauseContext objectComputerClause : ctx.objectComputerClause()) {
				if (objectComputerClause.memorySizeClause() != null) {
					result.addMemorySizeClause(objectComputerClause.memorySizeClause());
				}

				if (objectComputerClause.diskSizeClause() != null) {
					result.addDiskSizeClause(objectComputerClause.diskSizeClause());
				}

				if (objectComputerClause.collatingSequenceClause() != null) {
					result.addCollatingSequenceClause(objectComputerClause.collatingSequenceClause());
				}

				if (objectComputerClause.segmentLimitClause() != null) {
					result.addSegmentLimitClause(objectComputerClause.segmentLimitClause());
				}

				if (objectComputerClause.characterSetClause() != null) {
					result.addCharacterSetClause(objectComputerClause.characterSetClause());
				}
			}

			objectComputerParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SourceComputerParagraph addSourceComputerParagraph(final SourceComputerParagraphContext ctx) {
		SourceComputerParagraph result = (SourceComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SourceComputerParagraphImpl(name, programUnit, ctx);

			if (ctx.DEBUGGING() != null) {
				result.setDebuggingMode(true);
			}

			sourceComputerParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ObjectComputerParagraph getObjectComputerParagraph() {
		return objectComputerParagraph;
	}

	@Override
	public SourceComputerParagraph getSourceComputerParagraph() {
		return sourceComputerParagraph;
	}

}
