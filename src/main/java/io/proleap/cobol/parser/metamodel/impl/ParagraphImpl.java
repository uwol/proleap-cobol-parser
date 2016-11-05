/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Paragraph;
import io.proleap.cobol.parser.metamodel.ParagraphName;

public class ParagraphImpl extends CobolScopeImpl implements Paragraph {

	protected final ParagraphContext ctx;

	protected final String name;

	protected ParagraphName paragraphName;

	public ParagraphImpl(final String name, final CopyBook copyBook, final CobolScope superScope,
			final ParagraphContext ctx) {
		super(copyBook, superScope, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public void addParagraphName(final ParagraphName paragraphName) {
		this.paragraphName = paragraphName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ParagraphName getParagraphName() {
		return paragraphName;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
