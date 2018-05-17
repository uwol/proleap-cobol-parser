/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol;

import io.proleap.cobol.CobolParser.RerunEveryClockContext;
import io.proleap.cobol.CobolParser.RerunEveryOfContext;
import io.proleap.cobol.CobolParser.RerunEveryRecordsContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

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
