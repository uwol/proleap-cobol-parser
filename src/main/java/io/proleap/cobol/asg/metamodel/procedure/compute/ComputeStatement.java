/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.compute;

import java.util.List;

import io.proleap.cobol.CobolParser.ComputeStoreContext;
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
