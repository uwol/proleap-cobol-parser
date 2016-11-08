/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt;

import io.proleap.cobol.parser.metamodel.call.Call;

public interface CallValueStmt extends ValueStmt {

	Call getCall();

}
