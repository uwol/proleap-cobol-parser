/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectByContext;
import io.proleap.cobol.Cobol85Parser.InspectReplacingAllLeadingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.asg.metamodel.procedure.inspect.By;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ReplacingAllLeadingImpl extends InspectPhraseImpl implements ReplacingAllLeading {

	protected List<BeforeAfter> beforeAfters = new ArrayList<BeforeAfter>();

	protected By by;

	protected final InspectReplacingAllLeadingContext ctx;

	protected ValueStmt patternDataItemValueStmt;

	public ReplacingAllLeadingImpl(final ProgramUnit programUnit, final InspectReplacingAllLeadingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BeforeAfter addBeforeAfter(final InspectBeforeAfterContext ctx) {
		BeforeAfter result = (BeforeAfter) getASGElement(ctx);

		if (result == null) {
			result = createBeforeAfter(ctx);
			beforeAfters.add(result);
		}

		return result;
	}

	@Override
	public By addBy(final InspectByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = createBy(ctx);
			by = result;
		}

		return result;
	}

	@Override
	public List<BeforeAfter> getBeforeAfters() {
		return beforeAfters;
	}

	@Override
	public By getBy() {
		return by;
	}

	@Override
	public ValueStmt getPatternDataItemValueStmt() {
		return patternDataItemValueStmt;
	}

	@Override
	public void setPatternDataItemValueStmt(final ValueStmt patternDataItemValueStmt) {
		this.patternDataItemValueStmt = patternDataItemValueStmt;
	}

}
