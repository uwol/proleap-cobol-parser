/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AddToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.To;

public class AddToStatementImpl extends CobolDivisionElementImpl implements AddToStatement {

	protected final AddToStatementContext ctx;

	protected List<From> froms = new ArrayList<From>();

	protected List<To> tos = new ArrayList<To>();

	public AddToStatementImpl(final ProgramUnit programUnit, final AddToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFrom(final From from) {
		froms.add(from);
	}

	@Override
	public void addTo(final To to) {
		tos.add(to);
	}

	@Override
	public List<From> getFroms() {
		return froms;
	}

	@Override
	public List<To> getTos() {
		return tos;
	}
}
