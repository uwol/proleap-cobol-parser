/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.OpenIOStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.InputOutputPhrase;

public class InputOutputPhraseImpl extends CobolDivisionElementImpl implements InputOutputPhrase {

	protected final OpenIOStatementContext ctx;

	protected List<Call> fileCalls = new ArrayList<Call>();

	public InputOutputPhraseImpl(final ProgramUnit programUnit, final OpenIOStatementContext ctx) {
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
