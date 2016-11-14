/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.ClassClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseFromContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseThroughContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClauseThrough;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ClassClauseImpl extends CobolDivisionElementImpl implements ClassClause {

	protected List<ClassClauseThrough> classClauseThroughs = new ArrayList<ClassClauseThrough>();

	protected ValueStmt classNameValueStmt;

	protected final ClassClauseContext ctx;

	protected Type type;

	public ClassClauseImpl(final ProgramUnit programUnit, final ClassClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ClassClauseThrough addClassClauseThrough(final ClassClauseThroughContext ctx) {
		ClassClauseThrough result = (ClassClauseThrough) getASGElement(ctx);

		if (result == null) {
			result = new ClassClauseThroughImpl(programUnit, ctx);

			final ClassClauseFromContext fromContext = ctx.classClauseFrom();
			final ClassClauseToContext toContext = ctx.classClauseTo();

			/*
			 * from
			 */
			final ValueStmt fromValueStmt;

			if (fromContext.identifier() != null) {
				fromValueStmt = createCallValueStmt(fromContext.identifier());
			} else if (fromContext.literal() != null) {
				fromValueStmt = createLiteralValueStmt(fromContext.literal());
			} else {
				fromValueStmt = null;
			}

			result.setFrom(fromValueStmt);

			/*
			 * to
			 */
			final ValueStmt toValueStmt;

			if (toContext.identifier() != null) {
				toValueStmt = createCallValueStmt(toContext.identifier());
			} else if (toContext.literal() != null) {
				toValueStmt = createLiteralValueStmt(toContext.literal());
			} else {
				toValueStmt = null;
			}

			result.setTo(toValueStmt);

			classClauseThroughs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<ClassClauseThrough> getClassClauseThroughs() {
		return classClauseThroughs;
	}

	@Override
	public ValueStmt getClassNameValueStmt() {
		return classNameValueStmt;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setClassNameValueStmt(final ValueStmt classNameValueStmt) {
		this.classNameValueStmt = classNameValueStmt;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
