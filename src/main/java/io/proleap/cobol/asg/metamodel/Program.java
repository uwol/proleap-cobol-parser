/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import java.util.Collection;
import java.util.List;

public interface Program extends ASGElement {

	CompilationUnit getCompilationUnit(String name);

	Collection<CompilationUnit> getCompilationUnits();

	List<String> getLines();

	void registerCompilationUnit(CompilationUnit compilationUnit);

	void setLines(List<String> lines);
}
