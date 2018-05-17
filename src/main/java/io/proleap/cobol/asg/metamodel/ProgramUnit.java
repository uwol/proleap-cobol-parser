/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import io.proleap.cobol.CobolParser.DataDivisionContext;
import io.proleap.cobol.CobolParser.EnvironmentDivisionContext;
import io.proleap.cobol.CobolParser.IdentificationDivisionContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionContext;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;

public interface ProgramUnit extends CompilationUnitElement {

	DataDivision addDataDivision(DataDivisionContext ctx);

	EnvironmentDivision addEnvironmentDivision(EnvironmentDivisionContext ctx);

	IdentificationDivision addIdentificationDivision(IdentificationDivisionContext ctx);

	ProcedureDivision addProcedureDivision(ProcedureDivisionContext ctx);

	DataDivision getDataDivision();

	EnvironmentDivision getEnvironmentDivision();

	IdentificationDivision getIdentificationDivision();

	ProcedureDivision getProcedureDivision();

}
