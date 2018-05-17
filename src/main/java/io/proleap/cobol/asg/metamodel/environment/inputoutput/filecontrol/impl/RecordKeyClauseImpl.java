/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.CobolParser.RecordKeyClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordKeyClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RecordKeyClauseImpl extends CobolDivisionElementImpl implements RecordKeyClause {

	protected final RecordKeyClauseContext ctx;

	protected PasswordClause passwordClause;

	protected Call recordKeyCall;

	public RecordKeyClauseImpl(final ProgramUnit programUnit, final RecordKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public PasswordClause getPasswordClause() {
		return passwordClause;
	}

	@Override
	public Call getRecordKeyCall() {
		return recordKeyCall;
	}

	@Override
	public void setPasswordClause(final PasswordClause passwordClause) {
		this.passwordClause = passwordClause;
	}

	@Override
	public void setRecordKeyCall(final Call recordKeyCall) {
		this.recordKeyCall = recordKeyCall;
	}

}
