/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ClassClauseContext;
import io.proleap.cobol.CobolParser.ClassClauseFromContext;
import io.proleap.cobol.CobolParser.ClassClauseThroughContext;
import io.proleap.cobol.CobolParser.ClassClauseToContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassThrough;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ClassClauseImpl extends CobolDivisionElementImpl implements ClassClause {

	protected Call classCall;

	protected ClassClauseType classClauseType;

	protected List<ClassThrough> classThroughs = new ArrayList<ClassThrough>();

	protected final ClassClauseContext ctx;

	public ClassClauseImpl(final ProgramUnit programUnit, final ClassClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ClassThrough addClassThrough(final ClassClauseThroughContext ctx) {
		ClassThrough result = (ClassThrough) getASGElement(ctx);

		if (result == null) {
			result = new ClassThroughImpl(programUnit, ctx);

			/*
			 * from
			 */
			final ClassClauseFromContext fromContext = ctx.classClauseFrom();

			if (fromContext != null) {
				final ValueStmt fromValueStmt = createValueStmt(fromContext.identifier(), fromContext.literal());
				result.setFrom(fromValueStmt);
			}

			/*
			 * to
			 */
			final ClassClauseToContext toContext = ctx.classClauseTo();

			if (toContext != null) {
				final ValueStmt toValueStmt = createValueStmt(toContext.identifier(), toContext.literal());
				result.setTo(toValueStmt);
			}

			classThroughs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getClassCall() {
		return classCall;
	}

	@Override
	public ClassClauseType getClassClauseType() {
		return classClauseType;
	}

	@Override
	public List<ClassThrough> getClassThroughs() {
		return classThroughs;
	}

	@Override
	public void setClassCall(final Call classCall) {
		this.classCall = classCall;
	}

	@Override
	public void setClassClauseType(final ClassClauseType classClauseType) {
		this.classClauseType = classClauseType;
	}

}
