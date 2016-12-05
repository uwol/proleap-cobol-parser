/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.InitializeReplacingByContext;
import io.proleap.cobol.Cobol85Parser.InitializeReplacingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.By;
import io.proleap.cobol.asg.metamodel.procedure.initialize.Replacing;

public class ReplacingImpl extends CobolDivisionElementImpl implements Replacing {

	private final static Logger LOG = LogManager.getLogger(ReplacingImpl.class);

	protected List<By> bys = new ArrayList<By>();

	protected final InitializeReplacingPhraseContext ctx;

	public ReplacingImpl(final ProgramUnit programUnit, final InitializeReplacingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public By addBy(final InitializeReplacingByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			// type
			final By.Type type;

			if (ctx.ALPHABETIC() != null) {
				type = By.Type.Alphabetic;
			} else if (ctx.ALPHANUMERIC() != null) {
				type = By.Type.Alphanumeric;
			} else if (ctx.NATIONAL() != null) {
				type = By.Type.National;
			} else if (ctx.NUMERIC() != null) {
				type = By.Type.Numeric;
			} else if (ctx.ALPHANUMERIC_EDITED() != null) {
				type = By.Type.AlphanumericEdited;
			} else if (ctx.NUMERIC_EDITED() != null) {
				type = By.Type.NumericEdited;
			} else if (ctx.DBCS() != null) {
				type = By.Type.Dbcs;
			} else if (ctx.EGCS() != null) {
				type = By.Type.Egcs;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setType(type);

			// value call
			final Call valueCall;

			if (ctx.identifier() != null) {
				valueCall = createCall(ctx.identifier());
			} else if (ctx.literal() != null) {
				valueCall = createCall(ctx.literal());
			} else {
				valueCall = null;
			}

			result.setValueCall(valueCall);

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
