/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface ProgramUnitElement extends CompilationUnitElement {

	Call addCall(AssignmentNameContext ctx);

	Call addCall(CobolWordContext ctx);

	Call addCall(DataNameContext ctx);

	Call addCall(IdentifierContext ctx);

	Call addCall(ProcedureNameContext ctx);

	Call addCall(QualifiedDataNameContext ctx);

	IntegerLiteral addIntegerLiteral(IntegerLiteralContext ctx);

	Literal addLiteral(LiteralContext ctx);

	ProgramUnit getProgramUnit();
}
