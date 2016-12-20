/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultDivContext;
import io.proleap.cobol.Cobol85Parser.PowersContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface MultDivsValueStmt extends ValueStmt {

	MultDivValueStmt addMultDiv(MultDivContext ctx);

	PowersValueStmt addPowers(PowersContext ctx);

	List<MultDivValueStmt> getMultDivs();

	PowersValueStmt getPowers();

	@Override
	String getValue();

}
