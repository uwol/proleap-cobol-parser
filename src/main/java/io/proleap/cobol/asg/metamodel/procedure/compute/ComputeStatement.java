/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.compute;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;

/**
 * Assigns the value of an arithmetic expression to a specified reference.
 */
public interface ComputeStatement extends Statement {

	Store addStore(ComputeStoreContext ctx);

	ArithmeticValueStmt getArithmeticExpression();

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	List<Store> getStores();

	void setArithmeticExpression(ArithmeticValueStmt arithmeticExpression);

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);
}
