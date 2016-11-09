/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateWrittenParagraphContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.identification.AuthorParagraph;
import io.proleap.cobol.parser.metamodel.identification.DateCompiledParagraph;
import io.proleap.cobol.parser.metamodel.identification.DateWrittenParagraph;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivisionBody;
import io.proleap.cobol.parser.metamodel.identification.InstallationParagraph;
import io.proleap.cobol.parser.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.parser.metamodel.identification.SecurityParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

public class IdentificationDivisionImpl extends CobolDivisionImpl implements IdentificationDivision {

	private final static Logger LOG = LogManager.getLogger(IdentificationDivisionImpl.class);

	protected final IdentificationDivisionContext ctx;

	protected List<IdentificationDivisionBody> identificationDivisionBodies;

	protected ProgramIdParagraph programIdParagraph;

	public IdentificationDivisionImpl(final ProgramUnit programUnit, final IdentificationDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AuthorParagraph addAuthorParagraph(final AuthorParagraphContext ctx) {
		AuthorParagraph result = (AuthorParagraph) getASGElement(ctx);

		if (result == null) {
			result = new AuthorParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DateCompiledParagraph addDateCompiledParagraph(final DateCompiledParagraphContext ctx) {
		DateCompiledParagraph result = (DateCompiledParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateCompiledParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DateWrittenParagraph addDateWrittenParagraph(final DateWrittenParagraphContext ctx) {
		DateWrittenParagraph result = (DateWrittenParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateWrittenParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addIdentificationDivisionBody(final IdentificationDivisionBody identificationDivisionBody) {
		identificationDivisionBodies.add(identificationDivisionBody);
	}

	@Override
	public InstallationParagraph addInstallationParagraph(final InstallationParagraphContext ctx) {
		InstallationParagraph result = (InstallationParagraph) getASGElement(ctx);

		if (result == null) {
			result = new InstallationParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ProgramIdParagraph addProgramIdParagraph(final ProgramIdParagraphContext ctx) {
		ProgramIdParagraph result = (ProgramIdParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			result = new ProgramIdParagraphImpl(name, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RemarksParagraph addRemarksParagraph(final RemarksParagraphContext ctx) {
		RemarksParagraph result = (RemarksParagraph) getASGElement(ctx);

		if (result == null) {
			result = new RemarksParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SecurityParagraph addSecurityParagraph(final SecurityParagraphContext ctx) {
		SecurityParagraph result = (SecurityParagraph) getASGElement(ctx);

		if (result == null) {
			result = new SecurityParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<IdentificationDivisionBody> getIdentificationDivisionBodies() {
		return identificationDivisionBodies;
	}

	@Override
	public ProgramIdParagraph getProgramIdParagraph() {
		return programIdParagraph;
	}

	@Override
	public void setProgramIdParagraph(final ProgramIdParagraph programIdParagraph) {
		this.programIdParagraph = programIdParagraph;
	}

}
