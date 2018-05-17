/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment;

import io.proleap.cobol.CobolParser.ConfigurationSectionContext;
import io.proleap.cobol.CobolParser.InputOutputSectionContext;
import io.proleap.cobol.CobolParser.SpecialNamesParagraphContext;
import io.proleap.cobol.asg.metamodel.CobolDivision;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SpecialNamesParagraph;

/**
 * Describes the environment for which the program is written.
 */
public interface EnvironmentDivision extends CobolDivision {

	ConfigurationSection addConfigurationSection(ConfigurationSectionContext ctx);

	InputOutputSection addInputOutputSection(InputOutputSectionContext ctx);

	SpecialNamesParagraph addSpecialNamesParagraph(SpecialNamesParagraphContext ctx);

	ConfigurationSection getConfigurationSection();

	InputOutputSection getInputOutputSection();

	SpecialNamesParagraph getSpecialNamesParagraph();

}
