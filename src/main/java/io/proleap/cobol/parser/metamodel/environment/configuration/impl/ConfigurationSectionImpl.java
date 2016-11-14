/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.impl;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ConfigurationSectionImpl extends CobolDivisionElementImpl implements ConfigurationSection {

	protected final ConfigurationSectionContext ctx;

	protected ObjectComputerParagraph objectComputerParagraph;

	protected SourceComputerParagraph sourceComputerParagraph;

	public ConfigurationSectionImpl(final ProgramUnit programUnit, final ConfigurationSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ObjectComputerParagraph getObjectComputerParagraph() {
		return objectComputerParagraph;
	}

	@Override
	public SourceComputerParagraph getSourceComputerParagraph() {
		return sourceComputerParagraph;
	}

	@Override
	public void setObjectComputerParagraph(final ObjectComputerParagraph objectComputerParagraph) {
		this.objectComputerParagraph = objectComputerParagraph;
	}

	@Override
	public void setSourceComputerParagraph(final SourceComputerParagraph sourceComputerParagraph) {
		this.sourceComputerParagraph = sourceComputerParagraph;
	}

}
