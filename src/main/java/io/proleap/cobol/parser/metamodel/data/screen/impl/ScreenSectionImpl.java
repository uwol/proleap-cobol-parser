/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ScreenSectionImpl extends CobolDivisionElementImpl implements ScreenSection {

	protected final ScreenSectionContext ctx;

	public ScreenSectionImpl(final ProgramUnit programUnit, final ScreenSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
