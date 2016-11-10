/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;

public interface EnvironmentDivision extends CobolDivision {

	ConfigurationSection addConfigurationSection(ConfigurationSectionContext ctx);

	void addEnvironmentDivisionBody(EnvironmentDivisionBody environmentDivisionBody);

	FileControlEntry addFileControlEntry(FileControlEntryContext ctx);

	FileControlParagraph addFileControlParagraph(FileControlParagraphContext ctx);

	InputOutputSection addInputOutputSection(InputOutputSectionContext ctx);

	IoControlParagraph addIoControlParagraph(IoControlParagraphContext ctx);

	ObjectComputerParagraph addObjectComputerParagraph(ObjectComputerParagraphContext ctx);

	SourceComputerParagraph addSourceComputerParagraph(SourceComputerParagraphContext ctx);

	List<EnvironmentDivisionBody> getEnvironmentDivisionBodies();

}
