/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingCloseDispositionContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface Using extends CobolDivisionElement {

	enum Type {
		AssociatedData, AssociatedDataLength, CloseDisposition
	}

	UsingAssociatedData addUsingAssociatedData(ClosePortFileIOUsingAssociatedDataContext ctx);

	UsingAssociatedDataLength addUsingAssociatedDataLength(ClosePortFileIOUsingAssociatedDataLengthContext ctx);

	UsingCloseDisposition addUsingCloseDisposition(ClosePortFileIOUsingCloseDispositionContext ctx);

	Type getType();

	UsingAssociatedData getUsingAssociatedData();

	UsingAssociatedDataLength getUsingAssociatedDataLength();

	UsingCloseDisposition getUsingCloseDisposition();

	void setType(Type type);

}
