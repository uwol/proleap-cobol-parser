/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification;

import io.proleap.cobol.CobolParser.AuthorParagraphContext;
import io.proleap.cobol.CobolParser.DateCompiledParagraphContext;
import io.proleap.cobol.CobolParser.DateWrittenParagraphContext;
import io.proleap.cobol.CobolParser.InstallationParagraphContext;
import io.proleap.cobol.CobolParser.ProgramIdParagraphContext;
import io.proleap.cobol.CobolParser.RemarksParagraphContext;
import io.proleap.cobol.CobolParser.SecurityParagraphContext;
import io.proleap.cobol.asg.metamodel.CobolDivision;

/**
 * Names the program and may include the date it was written, the date of
 * compilation and other documentary information.
 */
public interface IdentificationDivision extends CobolDivision {

	AuthorParagraph addAuthorParagraph(AuthorParagraphContext ctx);

	DateCompiledParagraph addDateCompiledParagraph(DateCompiledParagraphContext ctx);

	DateWrittenParagraph addDateWrittenParagraph(DateWrittenParagraphContext ctx);

	InstallationParagraph addInstallationParagraph(InstallationParagraphContext ctx);

	ProgramIdParagraph addProgramIdParagraph(ProgramIdParagraphContext ctx);

	RemarksParagraph addRemarksParagraph(RemarksParagraphContext ctx);

	SecurityParagraph addSecurityParagraph(SecurityParagraphContext ctx);

	AuthorParagraph getAuthorParagraph();

	DateCompiledParagraph getDateCompiledParagraph();

	DateWrittenParagraph getDateWrittenParagraph();

	InstallationParagraph getInstallationParagraph();

	ProgramIdParagraph getProgramIdParagraph();

	RemarksParagraph getRemarksParagraph();

	SecurityParagraph getSecurityParagraph();

}
