/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.BasisContext;
import io.proleap.cobol.Cobol85Parser.PowerContext;
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
