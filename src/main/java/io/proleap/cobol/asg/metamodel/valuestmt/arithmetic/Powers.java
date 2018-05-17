/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import java.util.List;

import io.proleap.cobol.CobolParser.BasisContext;
import io.proleap.cobol.CobolParser.PowerContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface Powers extends ValueStmt {

	enum PowersType {
		MINUS, PLUS
	}

	Basis addBasis(BasisContext ctx);

	Power addPower(PowerContext ctx);

	Basis getBasis();

	List<Power> getPowers();

	PowersType getPowersType();

	void setPowersType(PowersType powersType);
}
