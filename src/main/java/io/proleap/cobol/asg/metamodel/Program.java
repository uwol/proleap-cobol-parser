/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel;

import java.util.List;

import io.proleap.cobol.asg.metamodel.registry.ASGElementRegistry;

public interface Program extends ASGElement {

	ASGElementRegistry getASGElementRegistry();

	CompilationUnit getCompilationUnit(String name);

	List<CompilationUnit> getCompilationUnits();

	void registerCompilationUnit(CompilationUnit compilationUnit);

}
