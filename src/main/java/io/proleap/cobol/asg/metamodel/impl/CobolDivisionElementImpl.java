/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.resolver.impl.NameResolverImpl;

public abstract class CobolDivisionElementImpl extends ProgramUnitElementImpl implements CobolDivisionElement {

	public CobolDivisionElementImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	protected String determineName(final ParserRuleContext ctx) {
		return new NameResolverImpl().determineName(ctx);
	}

	@Override
	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final ASGElement result = programUnit.getProgram().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		programUnit.getProgram().getASGElementRegistry().addASGElement(asgElement);
	}
}