/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call;

import java.util.List;

import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;

public interface TableCall extends DataDescriptionEntryCall {

	Subscript addSubscript(SubscriptContext ctx);

	List<Subscript> getSubscripts();
}
