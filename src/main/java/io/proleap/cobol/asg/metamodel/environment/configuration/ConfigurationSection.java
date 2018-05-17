/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration;

import io.proleap.cobol.CobolParser.ObjectComputerParagraphContext;
import io.proleap.cobol.CobolParser.SourceComputerParagraphContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;

public interface ConfigurationSection extends CobolDivisionElement {

	ObjectComputerParagraph addObjectComputerParagraph(ObjectComputerParagraphContext ctx);

	SourceComputerParagraph addSourceComputerParagraph(SourceComputerParagraphContext ctx);

	ObjectComputerParagraph getObjectComputerParagraph();

	SourceComputerParagraph getSourceComputerParagraph();

}
