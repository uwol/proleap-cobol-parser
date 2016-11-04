/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;

public class ProgramUnitImpl extends CobolScopeImpl implements ProgramUnit {

	protected IdentificationDivision identificationDivision;

	protected ProcedureDivision procedureDivision;

	public ProgramUnitImpl(final CopyBook copyBook, final CobolScope superScope, final ParseTree ctx) {
		super(copyBook, superScope, ctx);
	}

	@Override
	public IdentificationDivision getIdentificationDivision() {
		return identificationDivision;
	}

	@Override
	public ProcedureDivision getProcedureDivision() {
		return procedureDivision;
	}

	@Override
	public void setIdentificationDivision(final IdentificationDivision identificationDivision) {
		this.identificationDivision = identificationDivision;
	}

	@Override
	public void setProcedureDivision(final ProcedureDivision procedureDivision) {
		this.procedureDivision = procedureDivision;
	}

}
