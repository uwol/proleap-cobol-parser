/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateWrittenParagraphContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.AuthorParagraph;
import io.proleap.cobol.asg.metamodel.identification.DateCompiledParagraph;
import io.proleap.cobol.asg.metamodel.identification.DateWrittenParagraph;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.InstallationParagraph;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.asg.metamodel.identification.SecurityParagraph;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph.Attribute;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionImpl;

public class IdentificationDivisionImpl extends CobolDivisionImpl implements IdentificationDivision {

	protected AuthorParagraph authorParagraph;

	protected final IdentificationDivisionContext ctx;

	protected DateCompiledParagraph dateCompiledParagraph;

	protected DateWrittenParagraph dateWrittenParagraph;

	protected InstallationParagraph installationParagraph;

	protected ProgramIdParagraph programIdParagraph;

	protected RemarksParagraph remarksParagraph;

	protected SecurityParagraph securityParagraph;

	public IdentificationDivisionImpl(final ProgramUnit programUnit, final IdentificationDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AuthorParagraph addAuthorParagraph(final AuthorParagraphContext ctx) {
		AuthorParagraph result = (AuthorParagraph) getASGElement(ctx);

		if (result == null) {
			result = new AuthorParagraphImpl(programUnit, ctx);

			authorParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DateCompiledParagraph addDateCompiledParagraph(final DateCompiledParagraphContext ctx) {
		DateCompiledParagraph result = (DateCompiledParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateCompiledParagraphImpl(programUnit, ctx);

			dateCompiledParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DateWrittenParagraph addDateWrittenParagraph(final DateWrittenParagraphContext ctx) {
		DateWrittenParagraph result = (DateWrittenParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateWrittenParagraphImpl(programUnit, ctx);

			dateWrittenParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InstallationParagraph addInstallationParagraph(final InstallationParagraphContext ctx) {
		InstallationParagraph result = (InstallationParagraph) getASGElement(ctx);

		if (result == null) {
			result = new InstallationParagraphImpl(programUnit, ctx);

			installationParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ProgramIdParagraph addProgramIdParagraph(final ProgramIdParagraphContext ctx) {
		ProgramIdParagraph result = (ProgramIdParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			result = new ProgramIdParagraphImpl(name, programUnit, ctx);

			final Attribute attribute;

			if (ctx.COMMON() != null) {
				attribute = Attribute.Common;
			} else if (ctx.INITIAL() != null) {
				attribute = Attribute.Initial;
			} else if (ctx.LIBRARY() != null) {
				attribute = Attribute.Library;
			} else if (ctx.DEFINITION() != null) {
				attribute = Attribute.Definition;
			} else {
				attribute = null;
			}

			result.setAttribute(attribute);

			programIdParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RemarksParagraph addRemarksParagraph(final RemarksParagraphContext ctx) {
		RemarksParagraph result = (RemarksParagraph) getASGElement(ctx);

		if (result == null) {
			result = new RemarksParagraphImpl(programUnit, ctx);

			remarksParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SecurityParagraph addSecurityParagraph(final SecurityParagraphContext ctx) {
		SecurityParagraph result = (SecurityParagraph) getASGElement(ctx);

		if (result == null) {
			result = new SecurityParagraphImpl(programUnit, ctx);

			securityParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AuthorParagraph getAuthorParagraph() {
		return authorParagraph;
	}

	@Override
	public DateCompiledParagraph getDateCompiledParagraph() {
		return dateCompiledParagraph;
	}

	@Override
	public DateWrittenParagraph getDateWrittenParagraph() {
		return dateWrittenParagraph;
	}

	@Override
	public InstallationParagraph getInstallationParagraph() {
		return installationParagraph;
	}

	@Override
	public ProgramIdParagraph getProgramIdParagraph() {
		return programIdParagraph;
	}

	@Override
	public RemarksParagraph getRemarksParagraph() {
		return remarksParagraph;
	}

	@Override
	public SecurityParagraph getSecurityParagraph() {
		return securityParagraph;
	}

}
