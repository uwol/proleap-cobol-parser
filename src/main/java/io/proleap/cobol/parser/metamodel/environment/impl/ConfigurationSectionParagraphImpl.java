/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSectionParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolScopedElementImpl;

public abstract class ConfigurationSectionParagraphImpl extends CobolScopedElementImpl
		implements ConfigurationSectionParagraph {

	public ConfigurationSectionParagraphImpl(final CopyBook copyBook, final CobolScope superScope,
			final ParseTree ctx) {
		super(copyBook, superScope, ctx);
	}

}
