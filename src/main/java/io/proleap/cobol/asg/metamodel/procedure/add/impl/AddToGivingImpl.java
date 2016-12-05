/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.add.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AddToGivingStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.To;

public class AddToGivingImpl extends CobolDivisionElementImpl implements AddToGiving {

	protected final AddToGivingStatementContext ctx;

	protected List<From> froms = new ArrayList<From>();

	protected List<Giving> givings = new ArrayList<Giving>();

	protected List<To> tos = new ArrayList<To>();

	public AddToGivingImpl(final ProgramUnit programUnit, final AddToGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFrom(final From from) {
		froms.add(from);
	}

	@Override
	public void addGiving(final Giving giving) {
		givings.add(giving);
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
	public List<Giving> getGivings() {
		return givings;
	}

	@Override
	public List<To> getTos() {
		return tos;
	}
}
