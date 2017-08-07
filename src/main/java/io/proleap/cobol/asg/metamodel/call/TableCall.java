/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;

public interface TableCall extends DataDescriptionEntryCall {

	Subscript addSubscript(SubscriptContext ctx);

	List<Subscript> getSubscripts();
}
