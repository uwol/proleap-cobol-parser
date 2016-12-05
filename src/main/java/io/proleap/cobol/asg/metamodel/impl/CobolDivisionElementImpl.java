/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

public abstract class CobolDivisionElementImpl extends ProgramUnitElementImpl implements CobolDivisionElement {

	public CobolDivisionElementImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
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
	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		CobolParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}

}