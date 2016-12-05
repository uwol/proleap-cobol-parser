/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser.InspectAllLeadingsContext;
import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.Cobol85Parser.InspectByContext;
import io.proleap.cobol.Cobol85Parser.InspectCharactersContext;
import io.proleap.cobol.Cobol85Parser.InspectForContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.asg.metamodel.procedure.inspect.By;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;

public abstract class InspectPhraseImpl extends CobolDivisionElementImpl {

	public InspectPhraseImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

	protected BeforeAfter createBeforeAfter(final InspectBeforeAfterContext ctx) {
		BeforeAfter result = (BeforeAfter) getASGElement(ctx);

		if (result == null) {
			result = new BeforeAfterImpl(programUnit, ctx);

			// type
			final BeforeAfter.Type type;

			if (ctx.BEFORE() != null) {
				type = BeforeAfter.Type.Before;
			} else if (ctx.AFTER() != null) {
				type = BeforeAfter.Type.After;
			} else {
				type = null;
			}

			result.setType(type);

			// data item
			final Call dataItemCall = createCall(ctx.identifier(), ctx.literal());
			result.setDataItemCall(dataItemCall);

			registerASGElement(result);
		}

		return result;
	}

	protected By createBy(final InspectByContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			// by call
			final Call byCall = createCall(ctx.identifier(), ctx.literal());
			result.setByCall(byCall);

			registerASGElement(result);
		}

		return result;
	}

	protected For createFor(final InspectForContext ctx) {
		For result = (For) getASGElement(ctx);

		if (result == null) {
			result = new ForImpl(programUnit, ctx);

			// tally count
			final Call tallyCountDataItemCall = createCall(ctx.identifier());
			result.setTallyCountDataItemCall(tallyCountDataItemCall);

			// characters
			for (final InspectCharactersContext inspectCharactersContext : ctx.inspectCharacters()) {
				result.addCharacters(inspectCharactersContext);
			}

			// all leadings
			for (final InspectAllLeadingsContext inspectAllLeadingsContext : ctx.inspectAllLeadings()) {
				result.addAllLeadings(inspectAllLeadingsContext);
			}

			registerASGElement(result);
		}

		return result;
	}

}
