/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.ClassConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionNameReferenceContext;
import io.proleap.cobol.Cobol85Parser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.Cobol85Parser.InDataContext;
import io.proleap.cobol.Cobol85Parser.InMnemonicContext;
import io.proleap.cobol.Cobol85Parser.RelationConditionContext;
import io.proleap.cobol.Cobol85Parser.SimpleConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ClassCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameReference;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class SimpleConditionImpl extends ValueStmtImpl implements SimpleCondition {

	protected ClassCondition classCondition;

	protected ConditionValueStmt condition;

	protected ConditionNameReference conditionNameReference;

	protected SimpleConditionContext ctx;

	protected RelationConditionValueStmt relationCondition;

	public SimpleConditionImpl(final ProgramUnit programUnit, final SimpleConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public ClassCondition addClassCondition(final ClassConditionContext ctx) {
		ClassCondition result = (ClassCondition) getASGElement(ctx);

		if (result == null) {
			result = new ClassConditionImpl(programUnit, ctx);

			// identifier
			final Call identifierCall = createCall(ctx.identifier());
			result.setIdentifierCall(identifierCall);

			// not
			final boolean not = ctx.NOT() != null;
			result.setNot(not);

			// type
			final ClassCondition.Type type;

			if (ctx.NUMERIC() != null) {
				type = ClassCondition.Type.NUMERIC;
			} else if (ctx.ALPHABETIC() != null) {
				type = ClassCondition.Type.ALPHABETIC;
			} else if (ctx.ALPHABETIC_LOWER() != null) {
				type = ClassCondition.Type.ALPHABETIC_LOWER;
			} else if (ctx.ALPHABETIC_UPPER() != null) {
				type = ClassCondition.Type.ALPHABETIC_UPPER;
			} else if (ctx.DBCS() != null) {
				type = ClassCondition.Type.DBCS;
			} else if (ctx.KANJI() != null) {
				type = ClassCondition.Type.KANJI;
			} else if (ctx.className() != null) {
				type = ClassCondition.Type.CLASS_NAME;
			} else {
				type = null;
			}

			result.setType(type);

			// class call
			final Call classCall = createCall(ctx.className());
			result.setClassCall(classCall);

			classCondition = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public ConditionValueStmt addCondition(final ConditionContext ctx) {
		ConditionValueStmt result = (ConditionValueStmt) getASGElement(ctx);

		if (result == null) {
			result = createConditionValueStmt(ctx);

			condition = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public ConditionNameReference addConditionNameReference(final ConditionNameReferenceContext ctx) {
		ConditionNameReference result = (ConditionNameReference) getASGElement(ctx);

		if (result == null) {
			result = new ConditionNameReferenceImpl(programUnit, ctx);

			// condition name
			final Call conditionCall = createCall(ctx.conditionName());
			result.setConditionCall(conditionCall);

			// in data
			for (final InDataContext inDataContext : ctx.inData()) {
				result.addInData(inDataContext);
			}

			// in file
			if (ctx.inFile() != null) {
				result.addInFile(ctx.inFile());
			}

			// subscript
			for (final ConditionNameSubscriptReferenceContext conditionNameSubscriptReferenceContext : ctx
					.conditionNameSubscriptReference()) {
				result.addSubscriptReference(conditionNameSubscriptReferenceContext);
			}

			// mnemonic
			for (final InMnemonicContext inMnemonicContext : ctx.inMnemonic()) {
				result.addInMnemonic(inMnemonicContext);
			}

			conditionNameReference = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public RelationConditionValueStmt addRelationCondition(final RelationConditionContext ctx) {
		RelationConditionValueStmt result = (RelationConditionValueStmt) getASGElement(ctx);

		if (result == null) {
			result = createRelationConditionValueStmt(ctx);

			relationCondition = result;
			subValueStmts.add(result);
		}

		return result;
	}

	@Override
	public ClassCondition getClassCondition() {
		return classCondition;
	}

	@Override
	public ConditionValueStmt getCondition() {
		return condition;
	}

	@Override
	public ConditionNameReference getConditionNameReference() {
		return conditionNameReference;
	}

	@Override
	public RelationConditionValueStmt getRelationCondition() {
		return relationCondition;
	}

	@Override
	public String getValue() {
		return null;
	}

}
