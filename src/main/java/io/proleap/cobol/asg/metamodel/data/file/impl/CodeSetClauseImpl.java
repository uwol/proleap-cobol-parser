/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import io.proleap.cobol.CobolParser.CodeSetClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.file.CodeSetClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class CodeSetClauseImpl extends CobolDivisionElementImpl implements CodeSetClause {

	protected String alphabetName;

	protected final CodeSetClauseContext ctx;

	public CodeSetClauseImpl(final ProgramUnit programUnit, final CodeSetClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getAlphabetName() {
		return alphabetName;
	}

	@Override
	public void setAlhpabetName(final String alphabetName) {
		this.alphabetName = alphabetName;
	}

}
