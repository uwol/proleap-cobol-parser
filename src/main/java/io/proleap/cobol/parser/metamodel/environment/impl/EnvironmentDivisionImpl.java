/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSectionParagraph;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivisionBody;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

public class EnvironmentDivisionImpl extends CobolDivisionImpl implements EnvironmentDivision {

	private final static Logger LOG = LogManager.getLogger(EnvironmentDivisionImpl.class);

	protected final EnvironmentDivisionContext ctx;

	protected List<EnvironmentDivisionBody> environmentDivisionBodies;

	public EnvironmentDivisionImpl(final ProgramUnit programUnit, final EnvironmentDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ConfigurationSection addConfigurationSection(final ConfigurationSectionContext ctx) {
		ConfigurationSection result = (ConfigurationSection) getASGElement(ctx);

		if (result == null) {
			result = new ConfigurationSectionImpl(programUnit, this, ctx);

			for (final ConfigurationSectionParagraphContext configurationSectionParagraphContext : ctx
					.configurationSectionParagraph()) {
				final ConfigurationSectionParagraph configurationSectionParagraph;

				if (configurationSectionParagraphContext.sourceComputerParagraph() != null) {
					configurationSectionParagraph = addSourceComputerParagraph(
							configurationSectionParagraphContext.sourceComputerParagraph());
				} else if (configurationSectionParagraphContext.objectComputerParagraph() != null) {
					configurationSectionParagraph = addObjectComputerParagraph(
							configurationSectionParagraphContext.objectComputerParagraph());
				} else {
					LOG.warn("unknown configuration section paragraph {}", configurationSectionParagraphContext);
					configurationSectionParagraph = null;
				}

				result.addConfigurationSectionParagraph(configurationSectionParagraph);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addEnvironmentDivisionBody(final EnvironmentDivisionBody environmentDivisionBody) {
		environmentDivisionBodies.add(environmentDivisionBody);
	}

	@Override
	public ObjectComputerParagraph addObjectComputerParagraph(final ObjectComputerParagraphContext ctx) {
		ObjectComputerParagraph result = (ObjectComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ObjectComputerParagraphImpl(name, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SourceComputerParagraph addSourceComputerParagraph(final SourceComputerParagraphContext ctx) {
		SourceComputerParagraph result = (SourceComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SourceComputerParagraphImpl(name, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<EnvironmentDivisionBody> getEnvironmentDivisionBodies() {
		return environmentDivisionBodies;
	}
}
