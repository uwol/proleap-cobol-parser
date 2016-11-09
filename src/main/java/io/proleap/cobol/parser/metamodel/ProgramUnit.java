/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;

public interface ProgramUnit extends CobolScope {

	DataDivision getDataDivision();

	EnvironmentDivision getEnvironmentDivision();

	IdentificationDivision getIdentificationDivision();

	ProcedureDivision getProcedureDivision();

	void setDataDivision(DataDivision dataDivision);

	void setEnvironmentDivision(EnvironmentDivision environmentDivision);

	void setIdentificationDivision(IdentificationDivision identificationDivision);

	void setProcedureDivision(ProcedureDivision procedureDivision);
}
