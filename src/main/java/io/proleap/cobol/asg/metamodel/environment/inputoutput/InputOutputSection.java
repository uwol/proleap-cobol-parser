/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput;

import io.proleap.cobol.CobolParser.FileControlParagraphContext;
import io.proleap.cobol.CobolParser.IoControlParagraphContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;

public interface InputOutputSection extends CobolDivisionElement {

	FileControlParagraph addFileControlParagraph(FileControlParagraphContext ctx);

	IoControlParagraph addIoControlParagraph(IoControlParagraphContext ctx);

	FileControlParagraph getFileControlParagraph();

	IoControlParagraph getIoControlParagraph();

}
