/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.declaratives.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ProcedureDeclarativeContext;
import io.proleap.cobol.CobolParser.ProcedureDeclarativesContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declarative;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;

public class DeclarativesImpl extends CobolDivisionElementImpl implements Declaratives {

	protected final ProcedureDeclarativesContext ctx;

	protected List<Declarative> declaratives = new ArrayList<Declarative>();

	public DeclarativesImpl(final ProgramUnit programUnit, final ProcedureDeclarativesContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Declarative addDeclarative(final ProcedureDeclarativeContext ctx) {
		Declarative result = (Declarative) getASGElement(ctx);

		if (result == null) {
			result = new DeclarativeImpl(programUnit, ctx);

			result.addSectionHeader(ctx.procedureSectionHeader());
			result.addUseStatement(ctx.useStatement());

			declaratives.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Declarative> getDeclaratives() {
		return declaratives;
	}

}
