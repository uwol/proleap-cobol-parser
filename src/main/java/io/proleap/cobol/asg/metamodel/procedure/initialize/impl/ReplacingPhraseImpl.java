/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.InitializeReplacingByContext;
import io.proleap.cobol.CobolParser.InitializeReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.By;
import io.proleap.cobol.asg.metamodel.procedure.initialize.ReplacingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ReplacingPhraseImpl extends CobolDivisionElementImpl implements ReplacingPhrase {

	private final static Logger LOG = LoggerFactory.getLogger(ReplacingPhraseImpl.class);

	protected List<By> bys = new ArrayList<By>();

	protected final InitializeReplacingPhraseContext ctx;

	public ReplacingPhraseImpl(final ProgramUnit programUnit, final InitializeReplacingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public By addBy(final InitializeReplacingByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			// type
			final By.ByType type;

			if (ctx.ALPHABETIC() != null) {
				type = By.ByType.ALPHABETIC;
			} else if (ctx.ALPHANUMERIC() != null) {
				type = By.ByType.ALPHANUMERIC;
			} else if (ctx.ALPHANUMERIC_EDITED() != null) {
				type = By.ByType.ALPHANUMERIC_EDITED;
			} else if (ctx.NATIONAL() != null) {
				type = By.ByType.NATIONAL;
			} else if (ctx.NATIONAL_EDITED() != null) {
				type = By.ByType.NATIONAL_EDITED;
			} else if (ctx.NUMERIC() != null) {
				type = By.ByType.NUMERIC;
			} else if (ctx.NUMERIC_EDITED() != null) {
				type = By.ByType.NUMERIC_EDITED;
			} else if (ctx.DBCS() != null) {
				type = By.ByType.DBCS;
			} else if (ctx.EGCS() != null) {
				type = By.ByType.EGCS;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setByType(type);

			// value
			final ValueStmt valueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setValueStmt(valueStmt);

			bys.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<By> getBys() {
		return bys;
	}

}
