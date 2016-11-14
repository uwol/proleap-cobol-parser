/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.configuration.source.SourceComputerParagraph;

public interface ConfigurationSection extends CobolDivisionElement {

	ObjectComputerParagraph getObjectComputerParagraph();

	SourceComputerParagraph getSourceComputerParagraph();

	void setObjectComputerParagraph(ObjectComputerParagraph objectComputerParagraph);

	void setSourceComputerParagraph(SourceComputerParagraph sourceComputerParagraph);

}
