/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.ClassNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MnemonicNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.SystemNameContext;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface ProgramUnitElement extends CompilationUnitElement {

	Call addCall(AssignmentNameContext ctx);

	Call addCall(ClassNameContext ctx);

	Call addCall(CobolWordContext ctx);

	Call addCall(DataNameContext ctx);

	Call addCall(FileNameContext ctx);

	Call addCall(IdentifierContext ctx);

	Call addCall(ProcedureNameContext ctx);

	Call addCall(QualifiedDataNameContext ctx);

	Call addCall(ReportNameContext ctx);

	Call addCall(SystemNameContext ctx);

	IntegerLiteral addIntegerLiteral(IntegerLiteralContext ctx);

	Literal addLiteral(LiteralContext ctx);

	MnemonicName addMnemonicName(MnemonicNameContext ctx);

	ProgramUnit getProgramUnit();
}
