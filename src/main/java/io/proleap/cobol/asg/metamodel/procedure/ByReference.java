/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ByReference extends CobolDivisionElement {

	Call getReferenceCall();

	boolean isAny();

	boolean isOptional();

	void setAny(boolean any);

	void setOptional(boolean optional);

	void setReferenceCall(Call fileCall);
}
