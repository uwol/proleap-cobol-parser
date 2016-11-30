/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.AlternateRecordKeyClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.AlternateRecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class AlternateRecordKeyClauseImpl extends CobolDivisionElementImpl implements AlternateRecordKeyClause {

	protected final AlternateRecordKeyClauseContext ctx;

	protected Call dataCall;

	protected PasswordClause passwordClause;

	public AlternateRecordKeyClauseImpl(final ProgramUnit programUnit, final AlternateRecordKeyClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public PasswordClause getPasswordClause() {
		return passwordClause;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

	@Override
	public void setPasswordClause(final PasswordClause passwordClause) {
		this.passwordClause = passwordClause;
	}

}
