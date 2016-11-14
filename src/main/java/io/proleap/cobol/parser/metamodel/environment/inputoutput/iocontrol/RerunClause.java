/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol;

import io.proleap.cobol.Cobol85Parser.RerunEveryClockContext;
import io.proleap.cobol.Cobol85Parser.RerunEveryOfContext;
import io.proleap.cobol.Cobol85Parser.RerunEveryRecordsContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface RerunClause extends CobolDivisionElement {

	RerunEveryClock addRerunEveryClock(RerunEveryClockContext ctx);

	RerunEveryOf addRerunEveryOf(RerunEveryOfContext ctx);

	RerunEveryRecords addRerunEveryRecords(RerunEveryRecordsContext ctx);

	ValueStmt getOnValueStmt();

	RerunEveryClock getRerunEveryClock();

	RerunEveryOf getRerunEveryOf();

	RerunEveryRecords getRerunEveryRecords();

	void setOnValueStmt(ValueStmt onValueStmt);
}
