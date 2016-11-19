/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.programlibrary.impl;

import io.proleap.cobol.Cobol85Parser.LibraryAttributeClauseFormat2Context;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ImportAttributeImpl extends CobolDivisionElementImpl implements ImportAttribute {

	protected LibraryAttributeClauseFormat2Context ctx;

	protected Literal functionLiteral;

	protected Literal parameterLiteral;

	protected Literal titleLiteral;

	protected Type type;

	public ImportAttributeImpl(final ProgramUnit programUnit, final LibraryAttributeClauseFormat2Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Literal getFunctionLiteral() {
		return functionLiteral;
	}

	@Override
	public Literal getParameterLiteral() {
		return parameterLiteral;
	}

	@Override
	public Literal getTitleLiteral() {
		return titleLiteral;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setFunctionLiteral(final Literal functionLiteral) {
		this.functionLiteral = functionLiteral;
	}

	@Override
	public void setParameterLiteral(final Literal parameterLiteral) {
		this.parameterLiteral = parameterLiteral;
	}

	@Override
	public void setTitleLiteral(final Literal titleLiteral) {
		this.titleLiteral = titleLiteral;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}
}
