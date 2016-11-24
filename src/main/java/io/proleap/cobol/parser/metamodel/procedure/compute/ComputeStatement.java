/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.compute;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.Statement;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;

/**
 * Assigns the value of an arithmetic expression to a specified reference.
 */
public interface ComputeStatement extends Statement {

	Store addStore(ComputeStoreContext ctx);

	ArithmeticValueStmt getArithmeticExpression();

	NotOnSizeError getNotOnSizeError();

	OnSizeError getOnSizeError();

	List<Store> getStores();

	void setArithmeticExpression(ArithmeticValueStmt arithmeticExpression);

	void setNotOnSizeError(NotOnSizeError notOnSizeError);

	void setOnSizeError(OnSizeError onSizeError);
}
