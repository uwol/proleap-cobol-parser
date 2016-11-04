/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.applicationcontext;

import io.proleap.cobol.preprocessor.CobolPreprocessor;

public class CobolGrammarContext {

	protected static CobolGrammarContext instance;

	public static CobolGrammarContext getInstance() {
		if (instance == null) {
			instance = new CobolGrammarContext();
		}

		return instance;
	}

	protected CobolPreprocessor cobolPreprocessor;

	public CobolPreprocessor getCobolPreprocessor() {
		return cobolPreprocessor;
	}

	public void reset() {
		instance = null;
	}

	public void setCobolPreprocessor(final CobolPreprocessor cobolPreprocessor) {
		this.cobolPreprocessor = cobolPreprocessor;
	}
}
