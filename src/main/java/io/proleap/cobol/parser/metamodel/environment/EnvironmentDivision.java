/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;

public interface EnvironmentDivision extends CobolDivision {

	ConfigurationSection addConfigurationSection(ConfigurationSectionContext ctx);

	InputOutputSection addInputOutputSection(InputOutputSectionContext ctx);

	SpecialNamesParagraph addSpecialNamesParagraph(SpecialNamesParagraphContext ctx);

	ConfigurationSection getConfigurationSection();

	InputOutputSection getInputOutputSection();

	SpecialNamesParagraph getSpecialNamesParagraph();

}
