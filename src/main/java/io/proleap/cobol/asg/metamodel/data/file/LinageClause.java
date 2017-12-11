/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface LinageClause extends CobolDivisionElement {

	ValueStmt getFootingAtValueStmt();

	ValueStmt getLinesAtBottomValueStmt();

	ValueStmt getLinesAtTopValueStmt();

	ValueStmt getNumberOfLinesValueStmt();

	void setFootingAtValueStmt(ValueStmt footingAtValueStmt);

	void setLinesAtBottomValueStmt(ValueStmt linesAtBottomValueStmt);

	void setLinesAtTopValueStmt(ValueStmt linesAtTopValueStmt);

	void setNumberOfLinesValueStmt(ValueStmt numberOfLinesValueStmt);
}
