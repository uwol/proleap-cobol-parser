/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;

import io.proleap.cobol.CobolParser.ProgramUnitContext;

public interface CompilationUnit extends ASGElement, NamedElement {

	ProgramUnit addProgramUnit(ProgramUnitContext ctx);

	List<String> getLines();

	ProgramUnit getProgramUnit();

	List<ProgramUnit> getProgramUnits();

	CommonTokenStream getTokens();

	int incrementFillerCounter();

	void setLines(List<String> lines);
}
