/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.report.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public abstract class ReportGroupDescriptionEntryImpl extends CobolDivisionElementImpl
		implements ReportGroupDescriptionEntry {

	protected final ParseTree ctx;

	public ReportGroupDescriptionEntryImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
