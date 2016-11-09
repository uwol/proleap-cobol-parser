/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CompilationUnitContext;
import io.proleap.cobol.Cobol85Parser.ProgramUnitContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;

public class CopyBookImpl extends CobolScopeImpl implements CopyBook {

	protected CompilationUnitContext ctx;

	protected final String name;

	protected final Program program;

	protected final List<ProgramUnit> programUnits = new ArrayList<ProgramUnit>();

	public CopyBookImpl(final String name, final Program program, final CompilationUnitContext ctx) {
		super(null, program, ctx);

		this.name = name;
		this.program = program;
		this.ctx = ctx;
		copyBook = this;

		registerASGElement(this);
		program.registerCopyBook(this);
	}

	@Override
	public ProgramUnit addProgramUnit(final ProgramUnitContext ctx) {
		ProgramUnit result = (ProgramUnit) getASGElement(ctx);

		if (result == null) {
			result = new ProgramUnitImpl(this, this, ctx);

			storeScopedElement(result);
			programUnits.add(result);

			// identification division
			final IdentificationDivision identificationDivision = addIdentificationDivision(
					ctx.identificationDivision());
			result.setIdentificationDivision(identificationDivision);

			// environment division
			if (ctx.environmentDivision() != null) {
				final EnvironmentDivision environmentDivision = addEnvironmentDivision(ctx.environmentDivision());
				result.setEnvironmentDivision(environmentDivision);
			}

			// data division
			if (ctx.dataDivision() != null) {
				final DataDivision dataDivision = addDataDivision(ctx.dataDivision());
				result.setDataDivision(dataDivision);
			}

			// procedure division
			if (ctx.procedureDivision() != null) {
				final ProcedureDivision procedureDivision = addProcedureDivision(ctx.procedureDivision());
				result.setProcedureDivision(procedureDivision);
			}
		}

		return result;
	}

	@Override
	public CompilationUnitContext getCtx() {
		return ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<ProgramUnit> getProgramUnits() {
		return programUnits;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
