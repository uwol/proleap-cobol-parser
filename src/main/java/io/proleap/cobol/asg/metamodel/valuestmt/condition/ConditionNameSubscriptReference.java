/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ConditionNameSubscriptReference extends ValueStmt {

	Subscript addSubscript(SubscriptContext ctx);

	List<Subscript> getSubscripts();

}
