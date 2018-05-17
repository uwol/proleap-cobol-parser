/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import io.proleap.cobol.CobolParser.LibraryAttributeClauseFormat2Context;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ImportAttributeImpl extends CobolDivisionElementImpl implements ImportAttribute {

	protected LibraryAttributeClauseFormat2Context ctx;

	protected Literal functionLiteral;

	protected ImportAttributeType importAttributeType;

	protected Literal parameterLiteral;

	protected Literal titleLiteral;

	public ImportAttributeImpl(final ProgramUnit programUnit, final LibraryAttributeClauseFormat2Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Literal getFunctionLiteral() {
		return functionLiteral;
	}

	@Override
	public ImportAttributeType getImportAttributeType() {
		return importAttributeType;
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
	public void setFunctionLiteral(final Literal functionLiteral) {
		this.functionLiteral = functionLiteral;
	}

	@Override
	public void setImportAttributeType(final ImportAttributeType importAttributeType) {
		this.importAttributeType = importAttributeType;
	}

	@Override
	public void setParameterLiteral(final Literal parameterLiteral) {
		this.parameterLiteral = parameterLiteral;
	}

	@Override
	public void setTitleLiteral(final Literal titleLiteral) {
		this.titleLiteral = titleLiteral;
	}
}
