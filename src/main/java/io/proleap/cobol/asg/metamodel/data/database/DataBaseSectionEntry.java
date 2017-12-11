/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.database;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface DataBaseSectionEntry extends CobolDivisionElement {

	IntegerLiteral getIntegerLiteral();

	ValueStmt getValueStmt1();

	ValueStmt getValueStmt2();

	void setIntegerLiteral(IntegerLiteral integerLiteral);

	void setValueStmt1(ValueStmt valueStmt1);

	void setValueStmt2(ValueStmt valueStmt2);

}
