/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CompilationUnitElement;

public class CompilationUnitElementImpl extends ASGElementImpl implements CompilationUnitElement {

	public CompilationUnitElementImpl(final ParserRuleContext ctx) {
		super(ctx);
	}

	protected String determineName(final ParserRuleContext ctx) {
		return CobolParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final ASGElement result = CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		CobolParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}
}
