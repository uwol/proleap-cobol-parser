/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SortUsingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.UsingPhrase;

public class UsingPhraseImpl extends CobolDivisionElementImpl implements UsingPhrase {

	protected final SortUsingContext ctx;

	protected List<Call> fileCalls = new ArrayList<Call>();

	public UsingPhraseImpl(final ProgramUnit programUnit, final SortUsingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileCall(final Call fileCall) {
		fileCalls.add(fileCall);
	}

	@Override
	public List<Call> getFileCalls() {
		return fileCalls;
	}

}
