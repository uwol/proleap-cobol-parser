/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.metamodel.CobolDivision;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public abstract class CobolDivisionImpl extends ProgramUnitElementImpl implements CobolDivision {

	public CobolDivisionImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

}
