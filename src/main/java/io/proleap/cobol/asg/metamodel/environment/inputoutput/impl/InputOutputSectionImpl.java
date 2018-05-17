/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.impl;

import io.proleap.cobol.CobolParser.FileControlEntryContext;
import io.proleap.cobol.CobolParser.FileControlParagraphContext;
import io.proleap.cobol.CobolParser.InputOutputSectionContext;
import io.proleap.cobol.CobolParser.IoControlClauseContext;
import io.proleap.cobol.CobolParser.IoControlParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl.FileControlParagraphImpl;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl.IoControlParagraphImpl;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class InputOutputSectionImpl extends CobolDivisionElementImpl implements InputOutputSection {

	protected final InputOutputSectionContext ctx;

	protected FileControlParagraph fileControlParagraph;

	protected IoControlParagraph ioControlParagraph;

	public InputOutputSectionImpl(final ProgramUnit programUnit, final InputOutputSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public FileControlParagraph addFileControlParagraph(final FileControlParagraphContext ctx) {
		FileControlParagraph result = (FileControlParagraph) getASGElement(ctx);

		if (result == null) {
			result = new FileControlParagraphImpl(programUnit, ctx);

			for (final FileControlEntryContext fileControlEntryContext : ctx.fileControlEntry()) {
				result.addFileControlEntry(fileControlEntryContext);
			}

			fileControlParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IoControlParagraph addIoControlParagraph(final IoControlParagraphContext ctx) {
		IoControlParagraph result = (IoControlParagraph) getASGElement(ctx);

		if (result == null) {
			result = new IoControlParagraphImpl(programUnit, ctx);

			if (ctx.fileName() != null) {
				final Call fileCall = createCall(ctx.fileName());
				result.setFileCall(fileCall);
			}

			for (final IoControlClauseContext ioControlClauseContext : ctx.ioControlClause()) {
				if (ioControlClauseContext.rerunClause() != null) {
					result.addRerunClause(ioControlClauseContext.rerunClause());
				}

				if (ioControlClauseContext.sameClause() != null) {
					result.addSameClause(ioControlClauseContext.sameClause());
				}

				if (ioControlClauseContext.multipleFileClause() != null) {
					result.addMultipleFileClause(ioControlClauseContext.multipleFileClause());
				}

				if (ioControlClauseContext.commitmentControlClause() != null) {
					result.addCommitmentControlClause(ioControlClauseContext.commitmentControlClause());
				}
			}

			ioControlParagraph = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileControlParagraph getFileControlParagraph() {
		return fileControlParagraph;
	}

	@Override
	public IoControlParagraph getIoControlParagraph() {
		return ioControlParagraph;
	}

}
