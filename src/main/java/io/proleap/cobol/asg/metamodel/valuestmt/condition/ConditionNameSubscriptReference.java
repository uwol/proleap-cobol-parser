/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ConditionNameSubscriptReference extends ValueStmt {

	Subscript addSubscript(SubscriptContext ctx);

	List<Subscript> getSubscripts();

}
