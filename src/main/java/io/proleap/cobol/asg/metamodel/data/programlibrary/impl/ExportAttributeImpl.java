/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import io.proleap.cobol.CobolParser.LibraryAttributeClauseFormat1Context;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportAttribute;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ExportAttributeImpl extends CobolDivisionElementImpl implements ExportAttribute {

	protected LibraryAttributeClauseFormat1Context ctx;

	protected Sharing sharing;

	public ExportAttributeImpl(final ProgramUnit programUnit, final LibraryAttributeClauseFormat1Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Sharing getSharing() {
		return sharing;
	}

	@Override
	public void setSharing(final Sharing sharing) {
		this.sharing = sharing;
	}

}
