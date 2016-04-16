/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package org.cobol85.applicationcontext;

import org.cobol85.preprocessor.impl.Cobol85PreprocessorImpl;

public class Cobol85GrammarContextFactory {

	public static void configureDefaultApplicationContext() {

		// reset application context
		Cobol85GrammarContext.getInstance().reset();

		Cobol85GrammarContext.getInstance().setCobol85Preprocessor(new Cobol85PreprocessorImpl());
	}
}
