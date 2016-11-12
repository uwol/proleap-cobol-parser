/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface CobolDivision extends ProgramUnitElement {

	Call addCall(CobolWordContext ctx);

	Call addCall(IdentifierContext ctx);

	Call addCall(ProcedureNameContext ctx);

	IntegerLiteral addIntegerLiteral(IntegerLiteralContext ctx);

	Literal addLiteral(LiteralContext ctx);

}
