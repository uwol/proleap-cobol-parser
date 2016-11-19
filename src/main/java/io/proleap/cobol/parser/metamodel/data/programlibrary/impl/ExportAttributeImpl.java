/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryAttributeClauseFormat1Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ExportAttribute;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
