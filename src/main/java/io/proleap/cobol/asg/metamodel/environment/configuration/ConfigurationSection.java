/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration;

import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;

public interface ConfigurationSection extends CobolDivisionElement {

	ObjectComputerParagraph addObjectComputerParagraph(ObjectComputerParagraphContext ctx);

	SourceComputerParagraph addSourceComputerParagraph(SourceComputerParagraphContext ctx);

	ObjectComputerParagraph getObjectComputerParagraph();

	SourceComputerParagraph getSourceComputerParagraph();

}
