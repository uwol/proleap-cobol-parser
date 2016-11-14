/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.impl;

import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl.FileControlParagraphImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl.IoControlParagraphImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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
