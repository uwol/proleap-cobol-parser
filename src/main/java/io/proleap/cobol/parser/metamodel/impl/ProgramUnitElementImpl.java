/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.ASGElement;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.ProgramUnitElement;

public class ProgramUnitElementImpl extends CompilationUnitElementImpl implements ProgramUnitElement {

	protected ProgramUnit programUnit;

	public ProgramUnitElementImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(ctx);

		this.programUnit = programUnit;
	}

	@Override
	protected String determineName(final ParseTree ctx) {
		return CobolParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	@Override
	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public ProgramUnit getProgramUnit() {
		return programUnit;
	}

	@Override
	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		CobolParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}
}
