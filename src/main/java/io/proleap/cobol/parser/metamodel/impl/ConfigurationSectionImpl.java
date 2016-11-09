/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.ConfigurationSectionParagraph;
import io.proleap.cobol.parser.metamodel.CopyBook;

public class ConfigurationSectionImpl extends EnvironmentDivisionBodyImpl implements ConfigurationSection {

	protected List<ConfigurationSectionParagraph> configurationSectionParagraphs;

	protected final ConfigurationSectionContext ctx;

	public ConfigurationSectionImpl(final CopyBook copyBook, final CobolScope superScope,
			final ConfigurationSectionContext ctx) {
		super(copyBook, superScope, ctx);

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
