/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.applicationcontext;

import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CobolGrammarContextFactory {

	public static void configureDefaultApplicationContext() {
		CobolGrammarContext.getInstance().reset();

		CobolGrammarContext.getInstance().setCobolPreprocessor(new CobolPreprocessorImpl());
	}
}
