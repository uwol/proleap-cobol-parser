/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.resolver;

import org.antlr.v4.runtime.tree.ParseTree;

public interface NameResolver {

	String determineName(ParseTree ctx);

}
