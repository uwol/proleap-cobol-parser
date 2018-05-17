/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import io.proleap.cobol.CobolParser.SortDuplicatesPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.Duplicates;

public class DuplicatesImpl extends CobolDivisionElementImpl implements Duplicates {

	protected final SortDuplicatesPhraseContext ctx;

	protected boolean duplicatesInOrder;

	public DuplicatesImpl(final ProgramUnit programUnit, final SortDuplicatesPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isDuplicatesInOrder() {
		return duplicatesInOrder;
	}

	@Override
	public void setDuplicatesInOrder(final boolean duplicatesInOrder) {
		this.duplicatesInOrder = duplicatesInOrder;
	}

}
