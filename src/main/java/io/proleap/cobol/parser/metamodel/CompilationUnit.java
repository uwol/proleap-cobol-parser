/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ProgramUnitContext;

public interface CompilationUnit extends ASGElement, NamedElement {

	ProgramUnit addProgramUnit(ProgramUnitContext ctx);

	ProgramUnit getProgramUnit();

	List<ProgramUnit> getProgramUnits();
}
