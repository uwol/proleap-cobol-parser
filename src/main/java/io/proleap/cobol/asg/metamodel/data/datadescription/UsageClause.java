/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface UsageClause extends CobolDivisionElement {

	enum Type {
		Binary, BinaryExtended, BinaryTruncated, Bit, Comp, Comp1, Comp2, Comp3, Comp4, Comp5, ControlPoint, Date, Display, Display1, Double, Event, FunctionPointer, Index, Kanji, Lock, National, PackedDecimal, Pointer, ProcedurePointer, Real, Task
	}

	Type getType();

	void setType(Type type);
}
