/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package org.cobol85.applicationcontext;

import org.cobol85.preprocessor.Cobol85Preprocessor;

public class Cobol85GrammarContext {

	protected static Cobol85GrammarContext instance;

	public static Cobol85GrammarContext getInstance() {
		if (instance == null) {
			instance = new Cobol85GrammarContext();
		}

		return instance;
	}

	protected Cobol85Preprocessor cobol85Preprocessor;

	public Cobol85Preprocessor getCobol85Preprocessor() {
		return cobol85Preprocessor;
	}

	public void reset() {
		instance = null;
	}

	public void setCobol85Preprocessor(final Cobol85Preprocessor cobol85Preprocessor) {
		this.cobol85Preprocessor = cobol85Preprocessor;
	}
}
