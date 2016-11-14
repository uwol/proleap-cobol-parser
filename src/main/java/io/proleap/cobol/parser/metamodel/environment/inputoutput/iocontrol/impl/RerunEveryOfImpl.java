/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.RerunEveryOfContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.RerunEveryOf;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class RerunEveryOfImpl extends CobolDivisionElementImpl implements RerunEveryOf {

	protected final RerunEveryOfContext ctx;

	protected ValueStmt fileNameValueStmt;

	protected Type type;

	public RerunEveryOfImpl(final ProgramUnit programUnit, final RerunEveryOfContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFileNameValueStmt() {
		return fileNameValueStmt;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setFileNameValueStmt(final ValueStmt fileNameValueStmt) {
		this.fileNameValueStmt = fileNameValueStmt;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}
}
