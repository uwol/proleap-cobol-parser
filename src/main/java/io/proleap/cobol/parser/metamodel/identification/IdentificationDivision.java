/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification;

import java.util.List;

import io.proleap.cobol.parser.metamodel.CobolScopedElement;

public interface IdentificationDivision extends CobolScopedElement {

	void addIdentificationDivisionBody(IdentificationDivisionBody identificationDivisionBody);

	List<IdentificationDivisionBody> getIdentificationDivisionBodies();

	ProgramIdParagraph getProgramIdParagraph();

	void setProgramIdParagraph(ProgramIdParagraph programIdParagraph);

}
