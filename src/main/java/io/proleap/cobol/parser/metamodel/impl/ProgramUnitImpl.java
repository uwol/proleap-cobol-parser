/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.ProgramUnitContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;

public class ProgramUnitImpl extends CobolScopeImpl implements ProgramUnit {

	protected final ProgramUnitContext ctx;

	protected DataDivision dataDivision;

	protected EnvironmentDivision environmentDivision;

	protected IdentificationDivision identificationDivision;

	protected ProcedureDivision procedureDivision;

	public ProgramUnitImpl(final CopyBook copyBook, final CobolScope superScope, final ProgramUnitContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public DataDivision getDataDivision() {
		return dataDivision;
	}

	@Override
	public EnvironmentDivision getEnvironmentDivision() {
		return environmentDivision;
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
	public void setDataDivision(final DataDivision dataDivision) {
		this.dataDivision = dataDivision;
	}

	@Override
	public void setEnvironmentDivision(final EnvironmentDivision environmentDivision) {
		this.environmentDivision = environmentDivision;
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
