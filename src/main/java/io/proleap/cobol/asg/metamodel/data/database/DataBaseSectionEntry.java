/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
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
