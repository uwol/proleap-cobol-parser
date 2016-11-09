/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateWrittenParagraphContext;
import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;

public interface IdentificationDivision extends CobolDivision {

	AuthorParagraph addAuthorParagraph(AuthorParagraphContext ctx);

	DateCompiledParagraph addDateCompiledParagraph(DateCompiledParagraphContext ctx);

	DateWrittenParagraph addDateWrittenParagraph(DateWrittenParagraphContext ctx);

	void addIdentificationDivisionBody(IdentificationDivisionBody identificationDivisionBody);

	InstallationParagraph addInstallationParagraph(InstallationParagraphContext ctx);

	ProgramIdParagraph addProgramIdParagraph(ProgramIdParagraphContext ctx);

	RemarksParagraph addRemarksParagraph(RemarksParagraphContext ctx);

	SecurityParagraph addSecurityParagraph(SecurityParagraphContext ctx);

	List<IdentificationDivisionBody> getIdentificationDivisionBodies();

	ProgramIdParagraph getProgramIdParagraph();

	void setProgramIdParagraph(ProgramIdParagraph programIdParagraph);

}
