/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.CobolParser.CompilationUnitContext;
import io.proleap.cobol.CobolParser.ProgramUnitContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public class CompilationUnitImpl extends ASGElementImpl implements CompilationUnit {

	protected CompilationUnitContext ctx;

	protected int fillerCounter = 0;

	protected List<String> lines;

	protected final String name;

	protected final List<ProgramUnit> programUnits = new ArrayList<ProgramUnit>();

	protected CommonTokenStream tokens;

	public CompilationUnitImpl(final String name, final Program program, final CommonTokenStream tokens,
			final CompilationUnitContext ctx) {
		super(program, ctx);

		this.name = name;
		this.ctx = ctx;
		this.tokens = tokens;

		registerASGElement(this);
		program.registerCompilationUnit(this);
	}

	@Override
	public ProgramUnit addProgramUnit(final ProgramUnitContext ctx) {
		ProgramUnit result = (ProgramUnit) getASGElement(ctx);

		if (result == null) {
			result = new ProgramUnitImpl(this, ctx);

			registerASGElement(result);
			programUnits.add(result);
		}

		return result;
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final ASGElement result = program.getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public CompilationUnitContext getCtx() {
		return ctx;
	}

	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ProgramUnit getProgramUnit() {
		final ProgramUnit result;

		if (programUnits.isEmpty()) {
			result = null;
		} else {
			result = programUnits.get(0);
		}

		return result;
	}

	@Override
	public List<ProgramUnit> getProgramUnits() {
		return programUnits;
	}

	@Override
	public CommonTokenStream getTokens() {
		return tokens;
	}

	@Override
	public int incrementFillerCounter() {
		return fillerCounter++;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		program.getASGElementRegistry().addASGElement(asgElement);
	}

	@Override
	public void setLines(final List<String> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
