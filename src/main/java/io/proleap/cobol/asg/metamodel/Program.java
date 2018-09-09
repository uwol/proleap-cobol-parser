/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import java.util.List;

import io.proleap.cobol.asg.metamodel.registry.ASGElementRegistry;

public interface Program extends ASGElement {

	ASGElementRegistry getASGElementRegistry();

	CompilationUnit getCompilationUnit();

	CompilationUnit getCompilationUnit(String name);

	List<CompilationUnit> getCompilationUnits();

	void registerCompilationUnit(CompilationUnit compilationUnit);
}
