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

import io.proleap.cobol.CobolParser.AddToGivingStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.ToGiving;

public class AddToGivingStatementImpl extends CobolDivisionElementImpl implements AddToGivingStatement {

	protected final AddToGivingStatementContext ctx;

	protected List<From> froms = new ArrayList<From>();

	protected List<Giving> givings = new ArrayList<Giving>();

	protected List<ToGiving> tos = new ArrayList<ToGiving>();

	public AddToGivingStatementImpl(final ProgramUnit programUnit, final AddToGivingStatementContext ctx) {
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
	public void addTo(final ToGiving to) {
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
	public List<ToGiving> getTos() {
		return tos;
	}
}
