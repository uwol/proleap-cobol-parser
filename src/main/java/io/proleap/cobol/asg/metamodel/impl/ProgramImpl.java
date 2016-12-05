/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;

public class ProgramImpl extends ASGElementImpl implements Program {

	protected final Map<String, CompilationUnit> compilationUnits = new LinkedHashMap<String, CompilationUnit>();

	public ProgramImpl() {
		super(null);
	}

	@Override
	public CompilationUnit getCompilationUnit(final String name) {
		final String compilationUnitKey = getCompilationUnitKey(name);
		return compilationUnits.get(compilationUnitKey);
	}

	private String getCompilationUnitKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public Collection<CompilationUnit> getCompilationUnits() {
		return compilationUnits.values();
	}

	@Override
	public void registerCompilationUnit(final CompilationUnit compilationUnit) {
		final String compilationUnitKey = getCompilationUnitKey(compilationUnit.getName());
		compilationUnits.put(compilationUnitKey, compilationUnit);
	}
}
