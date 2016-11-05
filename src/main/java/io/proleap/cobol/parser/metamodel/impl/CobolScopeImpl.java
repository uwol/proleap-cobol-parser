/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.ASGElement;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CobolScopedElement;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.DisplayStatement;
import io.proleap.cobol.parser.metamodel.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.LineLabel;
import io.proleap.cobol.parser.metamodel.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.StopStatement;

public abstract class CobolScopeImpl extends CobolScopedElementImpl implements CobolScope {

	private final static Logger LOG = LogManager.getLogger(CobolScopeImpl.class);

	protected final List<CobolScopedElement> scopedElements = new ArrayList<CobolScopedElement>();

	public CobolScopeImpl(final CopyBook copyBook, final CobolScope superScope, final ParseTree ctx) {
		super(copyBook, superScope, ctx);
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public IdentificationDivision addIdentificationDivision(final IdentificationDivisionContext ctx) {
		IdentificationDivision result = (IdentificationDivision) getASGElement(ctx);

		if (result == null) {
			result = new IdentificationDivisionImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		// program id paragraph
		final ProgramIdParagraph programIdParagraph = addProgramIdParagraph(ctx.programIdParagraph());
		result.setProgramIdParagraph(programIdParagraph);

		return result;
	}

	@Override
	public LineLabel addLineLabel(final ParagraphNameContext ctx) {
		LineLabel result = (LineLabel) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new LineLabelImpl(name, copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ProcedureDivision addProcedureDivision(final ProcedureDivisionContext ctx) {
		ProcedureDivision result = (ProcedureDivision) getASGElement(ctx);

		if (result == null) {
			result = new ProcedureDivisionImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ProgramIdParagraph addProgramIdParagraph(final ProgramIdParagraphContext ctx) {
		ProgramIdParagraph result = (ProgramIdParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			result = new ProgramIdParagraphImpl(name, copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	protected String determineName(final ParseTree ctx) {
		return CobolParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		CobolParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}

	protected void storeScopedElement(final CobolScopedElement scopedElement) {
		assert scopedElement != null;
		assert scopedElement.getCtx() != null;

		registerASGElement(scopedElement);

		scopedElements.add(scopedElement);
	}

}
