/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSectionParagraph;

public class ConfigurationSectionImpl extends EnvironmentDivisionBodyImpl implements ConfigurationSection {

	protected List<ConfigurationSectionParagraph> configurationSectionParagraphs;

	protected final ConfigurationSectionContext ctx;

	public ConfigurationSectionImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final ConfigurationSectionContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addConfigurationSectionParagraph(final ConfigurationSectionParagraph configurationSectionParagraph) {
		configurationSectionParagraphs.add(configurationSectionParagraph);
	}

	@Override
	public List<ConfigurationSectionParagraph> getConfigurationSectionParagraphs() {
		return configurationSectionParagraphs;
	}

}
