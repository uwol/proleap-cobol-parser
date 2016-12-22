/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.in.impl;

import io.proleap.cobol.Cobol85Parser.InFileContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InFile;

public class InFileImpl extends CobolDivisionElementImpl implements InFile {

	protected InFileContext ctx;

	public InFileImpl(final ProgramUnit programUnit, final InFileContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
