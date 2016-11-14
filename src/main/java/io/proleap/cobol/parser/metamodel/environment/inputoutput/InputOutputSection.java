/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput;

import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;

public interface InputOutputSection extends CobolDivisionElement {

	FileControlParagraph addFileControlParagraph(FileControlParagraphContext ctx);

	IoControlParagraph addIoControlParagraph(IoControlParagraphContext ctx);

	FileControlParagraph getFileControlParagraph();

	IoControlParagraph getIoControlParagraph();

}
