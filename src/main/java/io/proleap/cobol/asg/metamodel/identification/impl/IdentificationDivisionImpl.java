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
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph.Attribute;
import io.proleap.cobol.asg.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.asg.metamodel.identification.SecurityParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.asg.util.TagUtils;
import io.proleap.cobol.preprocessor.CobolPreprocessor;

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

			if (ctx.commentEntry() != null) {
				final String author = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setAuthor(author);
			}

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

			if (ctx.commentEntry() != null) {
				final String dateCompiled = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setDateCompiled(dateCompiled);
			}

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

			if (ctx.commentEntry() != null) {
				final String dateWritten = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setDateWritten(dateWritten);
			}

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

			if (ctx.commentEntry() != null) {
				final String installation = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setInstallation(installation);
			}

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
			programIdParagraph = result;

			final Attribute attribute;

			if (ctx.COMMON() != null) {
				attribute = Attribute.COMMON;
			} else if (ctx.INITIAL() != null) {
				attribute = Attribute.INITIAL;
			} else if (ctx.LIBRARY() != null) {
				attribute = Attribute.LIBRARY;
			} else if (ctx.DEFINITION() != null) {
				attribute = Attribute.DEFINITION;
			} else {
				attribute = null;
			}

			result.setAttribute(attribute);

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

			if (ctx.commentEntry() != null) {
				final String remarks = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setRemarks(remarks);
			}

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

			if (ctx.commentEntry() != null) {
				final String security = TagUtils.getUntaggedText(ctx.commentEntry().COMMENTENTRYLINE(),
						CobolPreprocessor.COMMENT_ENTRY_TAG, CobolPreprocessor.END_EXEC_TAG);
				result.setSecurity(security);
			}

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
